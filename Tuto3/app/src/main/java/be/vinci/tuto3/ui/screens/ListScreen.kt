package be.vinci.tuto3.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.twotone.Favorite
import androidx.compose.material.icons.twotone.PlaylistAddCheckCircle
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import be.vinci.tuto3.R
import be.vinci.tuto3.data.Movie
import be.vinci.tuto3.data.defaultMovies
import be.vinci.tuto3.ui.theme.Tuto3Theme

@Composable
fun ListScreen(
    movies: List<Movie>,
    goToCreate: () -> Unit,
    goToDetail: (Movie) -> Unit,
    deleteMovie: (Movie) -> Unit,
) {
    var filterWatchList by rememberSaveable { mutableStateOf(false) }
    var filterLikes by rememberSaveable { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = when {
                        filterWatchList && filterLikes -> stringResource(R.string.liked_watchlist)
                        filterWatchList -> stringResource(R.string.watchlist)
                        filterLikes -> stringResource(R.string.liked_movies)
                        else -> stringResource(R.string.list_of_movies)
                    })
                },
                actions = {
                    IconButton(onClick = { filterWatchList = !filterWatchList }) {
                        Icon(
                            if (filterWatchList) Icons.Default.PlaylistAddCheckCircle
                            else Icons.TwoTone.PlaylistAddCheckCircle,
                            contentDescription = stringResource(R.string.filter_watchlist),
                        )
                    }
                    IconButton(onClick = { filterLikes = !filterLikes }) {
                        Icon(
                            if (filterLikes) Icons.Default.Favorite
                            else Icons.TwoTone.Favorite,
                            contentDescription = stringResource(R.string.filter_likes),
                        )
                    }
                },
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = goToCreate) {
                Icon(Icons.Default.Add, contentDescription = stringResource(R.string.create))
            }
        },
    ) { ListContent(movies, filterWatchList, filterLikes, goToDetail, deleteMovie) }
}

@Composable
fun ListContent(
    movies: List<Movie>,
    filterWatchList: Boolean,
    filterLikes: Boolean,
    goToDetail: (Movie) -> Unit,
    deleteMovie: (Movie) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        for (movie in movies) {
            if (filterWatchList && !movie.added) continue
            if (filterLikes && !movie.liked) continue

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .clickable { goToDetail(movie) }
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(text = movie.title, style = MaterialTheme.typography.h6)
                IconButton(onClick = { deleteMovie(movie) }) {
                    Icon(Icons.Default.Delete, contentDescription = stringResource(R.string.delete))
                }
            }
            Divider()
        }

    }
}

@Preview
@Composable
fun ListPreview() {
    Tuto3Theme { ListScreen(defaultMovies, {}, {}, {}) }
}
