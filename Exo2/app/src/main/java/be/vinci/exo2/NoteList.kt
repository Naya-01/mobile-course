package be.vinci.exo2

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.NoteAdd
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import be.vinci.exo2.ui.theme.Exo2Theme

@Composable
fun NoteList(
    notes: MutableList<Note>, action: (Note) -> Unit,
    title: String,
    content: String,
    changeTitle: (String) -> Unit,
    changeContent: (String) -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "List of notes") })
        },
    ) {

        Column(
            modifier = Modifier
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
        ) {
            for (note in notes) {
                Row(
                    modifier = Modifier
                        .clickable { action(note) }
                        .fillMaxWidth()
                        .padding(16.dp),
                ) {
                    Box(
                        modifier = Modifier
                            .width(50.dp)
                            .height(50.dp)
                            .weight(3f),
                    ) {
                        Text(
                            text = note.title,
                            style = MaterialTheme.typography.body1,
                            modifier = Modifier.align(Alignment.CenterStart)
                        )
                    }
                    Surface(
                        modifier = Modifier
                            .width(50.dp)
                            .height(50.dp)
                            .weight(1f)
                    ) {
                        IconButton(onClick = { notes.remove(note) }) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = null,
                            )
                        }
                    }

                }

                Divider()
            }
            AddNote(
                title = title,
                content = content,
                changeTitle = changeTitle,
                changeContent = changeContent,
                notes = notes,
            )
        }
    }
}


@Composable
fun AddNote(
    title: String,
    content: String,
    changeTitle: (String) -> Unit,
    changeContent: (String) -> Unit,
    notes: MutableList<Note>,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
    ) {
        OutlinedTextField(
            value = title,
            onValueChange = changeTitle,
            label = { Text(text = "New note title") },
            modifier = Modifier
                .align(Alignment.Center)
                .width(300.dp),
        )
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
    ) {
        OutlinedTextField(
            value = content,
            onValueChange = changeContent,
            label = { Text(text = "New content") },
            modifier = Modifier
                .align(Alignment.Center)
                .width(300.dp),

            )
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
    ) {
        Button(
            onClick = { notes.add(Note(getId(notes), title = title, content = content)) },
            contentPadding = PaddingValues(
                start = 20.dp,
                top = 12.dp,
                end = 20.dp,
                bottom = 12.dp
            ),
            modifier = Modifier.align(Alignment.Center)
        ) {
            Text("Create new note")
            Spacer(Modifier.size(ButtonDefaults.IconSpacing))
            Icon(
                Icons.Filled.NoteAdd,
                contentDescription = "Favorite",
                modifier = Modifier.size(ButtonDefaults.IconSize)
            )
        }
    }


}

fun getId(notes: List<Note>): Int {
    if(notes.isEmpty()) return 1;
    return notes.last().id+1;
}

@Preview(showBackground = true)
@Composable
fun DefaultPreviewNoteList() {
    Exo2Theme() {
        Column() {
            NoteList(notes = mutableListOf(), action = {}, "", "", {}, {})
        }
    }
}