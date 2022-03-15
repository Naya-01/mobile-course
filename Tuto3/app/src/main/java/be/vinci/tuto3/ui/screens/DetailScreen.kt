package be.vinci.tuto3.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import be.vinci.tuto3.R
import be.vinci.tuto3.data.Movie
import be.vinci.tuto3.data.defaultMovies
import be.vinci.tuto3.ui.theme.Tuto3Theme

@Composable
fun DetailScreen(
    movie: Movie,
    goBack: () -> Unit,
    goToUpdate: (Movie) -> Unit,
    toggleList: (Movie) -> Unit,
    toggleLike: (Movie) -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(R.string.movie_detail)) },
                navigationIcon = {
                    IconButton(onClick = goBack) {
                        Icon(Icons.Default.ArrowBack,
                            contentDescription = stringResource(R.string.back))
                    }
                },
                actions = {
                    IconButton(onClick = { toggleList(movie) }) {
                        Icon(
                            if (movie.added) Icons.Default.PlaylistAddCheck
                            else Icons.Default.PlaylistAdd,
                            contentDescription = stringResource(R.string.add_to_list),
                        )
                    }
                    IconButton(onClick = { toggleLike(movie) }) {
                        Icon(
                            if (movie.liked) Icons.Default.Favorite
                            else Icons.Default.FavoriteBorder,
                            contentDescription = stringResource(R.string.like),
                        )
                    }
                },
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { goToUpdate(movie) }) {
                Icon(Icons.Default.Edit, contentDescription = stringResource(R.string.update))
            }
        },
    ) { DetailContent(movie) }
}

@Composable
fun DetailContent(movie: Movie) {
    Column(
        modifier = Modifier
            .padding(32.dp)
            .fillMaxHeight()
            .verticalScroll(rememberScrollState())
    ) {
        Text(text = movie.title, style = MaterialTheme.typography.h3)
        Spacer(modifier = Modifier.height(16.dp))
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            Text(
                text = movie.director,
                style = MaterialTheme.typography.h6,
            )
            Text(
                text = movie.year.toString(),
                style = MaterialTheme.typography.h6,
            )
        }
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = movie.critic,
            style = MaterialTheme.typography.body1,
            textAlign = TextAlign.Justify,
        )
    }
}

@Preview
@Composable
fun DetailScreenPreview() {
    Tuto3Theme { DetailScreen(defaultMovies[0], {}, {}, {}, {}) }
}
