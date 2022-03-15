package be.vinci.tuto3.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import be.vinci.tuto3.R
import be.vinci.tuto3.data.Movie
import be.vinci.tuto3.data.defaultMovies
import be.vinci.tuto3.ui.theme.Tuto3Theme
import java.util.*

@Composable
fun CreateUpdateScreen(
    oldMovie: Movie?,
    goBack: () -> Unit,
    saveMovieAndRedirect: (String, String, Int, Boolean, Boolean, String) -> Unit,
) {
    val actualYear = Calendar.getInstance().get(Calendar.YEAR)

    var title by remember { mutableStateOf(oldMovie?.title ?: "") }
    var director by remember { mutableStateOf(oldMovie?.director ?: "") }
    var year by remember { mutableStateOf(oldMovie?.year ?: actualYear) }
    var added by remember { mutableStateOf(oldMovie?.added ?: false) }
    var liked by remember { mutableStateOf(oldMovie?.liked ?: false) }
    var critic by remember { mutableStateOf(oldMovie?.critic ?: "") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = if (oldMovie == null) stringResource(R.string.create_movie)
                        else stringResource(R.string.update_movie)
                    )
                },
                navigationIcon = {
                    IconButton(onClick = goBack) {
                        Icon(Icons.Default.ArrowBack,
                            contentDescription = stringResource(R.string.back))
                    }
                },
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                saveMovieAndRedirect(title, director, year, added, liked, critic)
            }) { Icon(Icons.Default.Save, contentDescription = stringResource(R.string.save)) }
        },
    ) {
        CreateUpdateContent(
            title = title, changeTitle = { title = it },
            director = director, changeDirector = { director = it },
            year = year, changeYear = { year = it },
            added = added, toggleList = { added = !added },
            liked = liked, toggleLike = { liked = !liked },
            critic = critic, changeCritic = { critic = it },
        )
    }
}

@Composable
fun CreateUpdateContent(
    title: String, changeTitle: (String) -> Unit,
    director: String, changeDirector: (String) -> Unit,
    year: Int, changeYear: (Int) -> Unit,
    added: Boolean, toggleList: () -> Unit,
    liked: Boolean, toggleLike: () -> Unit,
    critic: String, changeCritic: (String) -> Unit,
) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxHeight()
            .verticalScroll(rememberScrollState())
    ) {

        OutlinedTextField(
            value = title,
            onValueChange = changeTitle,
            label = { Text(text = stringResource(R.string.title)) },
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = director,
            onValueChange = changeDirector,
            label = { Text(text = stringResource(R.string.director)) },
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = year.toString(),
            onValueChange = {
                if (it == "") changeYear(0)
                else if (it.isDigitsOnly()) changeYear(it.toInt())
            },
            label = { Text(text = stringResource(R.string.year)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
        ) {
            Text(text = stringResource(R.string.in_watchlist))
            Switch(
                checked = added,
                onCheckedChange = { toggleList() },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = MaterialTheme.colors.primary,
                    checkedTrackColor = MaterialTheme.colors.secondary
                ),
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
        ) {
            Text(text = stringResource(R.string.liked))
            Switch(
                checked = liked,
                onCheckedChange = { toggleLike() },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = MaterialTheme.colors.primary,
                    checkedTrackColor = MaterialTheme.colors.secondary
                ),
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = critic,
            onValueChange = changeCritic,
            label = { Text(text = stringResource(R.string.critic)) },
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
        )

    }
}

@Preview
@Composable
fun CreateUpdatePreview() {
    Tuto3Theme { CreateUpdateScreen(defaultMovies[0], {}, { _, _, _, _, _, _ -> }) }
}
