package be.vinci.exo3.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import be.vinci.exo3.R
import be.vinci.exo3.data.ToDo
import be.vinci.exo3.data.defaultToDos
import be.vinci.exo3.ui.theme.Exo3Theme

@Composable
fun ToDoScreen(
    toDoList: List<ToDo>,
    toggleImportance: (ToDo) -> Unit,
    setDone: (ToDo) -> Unit,
    saveToDo: (String, Boolean) -> Unit,
) {
    var filterImportant by remember { mutableStateOf(false) }
    var showDone by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(R.string.todo_list)) },
                actions = {
                    IconButton(onClick = { filterImportant = !filterImportant }) {
                        Icon(
                            Icons.Default.Star,
                            contentDescription = if (filterImportant) stringResource(R.string.filtering_important)
                                                 else stringResource(R.string.not_filtering_important),
                            modifier = Modifier.alpha(if (filterImportant) 1f else 0.4f)
                        )
                    }
                    IconButton(onClick = { showDone = !showDone }) {
                        Icon(
                            Icons.Default.CheckBox,
                            contentDescription = if (showDone) stringResource(R.string.show_done)
                                                 else stringResource(R.string.hide_done),
                            modifier = Modifier.alpha(if (showDone) 1f else 0.4f)
                        )
                    }
                },
            )
        },

    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(16.dp),
        ) {
            ToDoList(
                toDoList = toDoList,
                filterImportant = filterImportant,
                showDone = showDone,
                toggleImportance = toggleImportance,
                setDone = setDone,
                modifier = Modifier.weight(1f),
            )
            Spacer(modifier = Modifier.height(16.dp))
            CreateToDoForm(saveToDo = saveToDo)
        }
    }
}

@Composable
fun ToDoList(
    toDoList: List<ToDo>,
    filterImportant: Boolean,
    showDone: Boolean,
    toggleImportance: (ToDo) -> Unit,
    setDone: (ToDo) -> Unit,
    modifier: Modifier = Modifier,
) {
    var expanded by rememberSaveable { mutableStateOf<Long?>(null) }

    Column(modifier = modifier.verticalScroll(rememberScrollState())) {
        for (toDo in toDoList) {
            if (filterImportant && !toDo.important) continue
            if (!showDone && toDo.done) continue

            ToDoRow(
                toDo = toDo,
                isExpanded = toDo.id == expanded,
                toggleExpanded = { expanded = if (expanded == it.id) null else it.id },
                toggleImportance = toggleImportance,
                setDone = setDone,
            )

            Divider()
        }
    }
}

@Composable
fun ToDoRow(
    toDo: ToDo,
    isExpanded: Boolean,
    toggleExpanded: (ToDo) -> Unit,
    toggleImportance: (ToDo) -> Unit,
    setDone: (ToDo) -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier
            .padding(4.dp)
            .clickable { toggleExpanded(toDo) },
        shape = MaterialTheme.shapes.medium,
        elevation = if (isExpanded && !toDo.done) 3.dp else 0.dp,
        color = if (isExpanded && !toDo.done) MaterialTheme.colors.primarySurface
                else MaterialTheme.colors.background,
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            if (!toDo.done) {
                IconButton(onClick = { toggleImportance(toDo) }) {
                    if (toDo.important) Icon(
                        Icons.Default.Star,
                        contentDescription = stringResource(R.string.important),
                        tint = if (isExpanded) MaterialTheme.colors.onPrimary
                               else MaterialTheme.colors.primary,
                    )
                    else Icon(
                        Icons.Default.StarOutline,
                        contentDescription = stringResource(R.string.not_important),
                        tint = if (isExpanded) MaterialTheme.colors.onPrimary
                               else MaterialTheme.colors.onBackground
                    )
                }
            } else Spacer(modifier = Modifier.width(12.dp))

            Text(
                text = toDo.text,
                maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                overflow = TextOverflow.Ellipsis,
                color = if (isExpanded && !toDo.done) MaterialTheme.colors.onPrimary
                        else MaterialTheme.colors.onBackground,
                modifier = Modifier
                    .padding(8.dp)
                    .weight(1f)
                    .alpha(if (toDo.done) 0.4f else 1f),
            )

            IconButton(onClick = { setDone(toDo) }, enabled = !toDo.done) {
                if (toDo.done) Icon(
                    Icons.Default.CheckBox,
                    contentDescription = stringResource(R.string.done),
                    modifier = Modifier.alpha(0.4f),
                    tint = if (isExpanded && !toDo.done) MaterialTheme.colors.onPrimary
                           else MaterialTheme.colors.onBackground,
                )
                else Icon(
                    Icons.Default.CheckBoxOutlineBlank,
                    contentDescription = stringResource(R.string.not_done),
                    tint = if (isExpanded && !toDo.done) MaterialTheme.colors.onPrimary
                           else MaterialTheme.colors.onBackground,
                )
            }
        }
    }
}

@Composable
fun CreateToDoForm(saveToDo: (String, Boolean) -> Unit, modifier: Modifier = Modifier) {
    var text by remember { mutableStateOf("") }
    var important by remember { mutableStateOf(false) }

    Column(modifier = modifier) {
        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            label = { Text(text = stringResource(R.string.new_todo)) },
            maxLines = 1,
            modifier = Modifier.fillMaxWidth(),
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Switch(
                    checked = important,
                    onCheckedChange = { important = !important },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = MaterialTheme.colors.primary,
                        checkedTrackColor = MaterialTheme.colors.primaryVariant
                    ),
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = stringResource(R.string.important), style = MaterialTheme.typography.body1)
            }

            Button(onClick = {
                saveToDo(text, important)
                text = ""
                important = false
            }) { Text(text = stringResource(R.string.create_todo)) }
        }
    }
}

@Preview
@Composable
fun ListPreview() {
    Exo3Theme {
        ToDoScreen(defaultToDos, {},{},saveToDo = { titre,bool -> } )
    }
}