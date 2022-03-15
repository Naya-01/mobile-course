package be.vinci.tuto3

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import be.vinci.tuto3.data.MovieViewModel
import be.vinci.tuto3.ui.screens.CreateUpdateScreen
import be.vinci.tuto3.ui.screens.DetailScreen
import be.vinci.tuto3.ui.screens.ListScreen
import be.vinci.tuto3.ui.theme.Tuto3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Tuto3Theme {

                val movieViewModel = viewModel<MovieViewModel>()
                val navController = rememberNavController()

                NavHost(navController, startDestination = "/movies") {

                    composable("/movies") {
                        ListScreen(
                            movies = movieViewModel.movies,
                            goToCreate = { navController.navigate("/movies/create") },
                            goToDetail = { navController.navigate("/movies/detail/${it.id}") },
                            deleteMovie = movieViewModel::deleteMovie,
                        )
                    }

                    composable("/movies/create") {
                        CreateUpdateScreen(
                            oldMovie = null,
                            goBack = { navController.popBackStack() },
                            saveMovieAndRedirect = { title, director, year, added, liked, critic ->
                                movieViewModel.createMovie(
                                    title = title,
                                    director = director,
                                    year = year,
                                    added = added,
                                    liked = liked,
                                    critic = critic
                                )
                                navController.popBackStack()
                            },
                        )
                    }

                    composable("/movies/detail/{id}") { navBackStackEntry ->
                        val id = navBackStackEntry.arguments?.getString("id")?.toLongOrNull()
                        if (id == null) {
                            Log.wtf("Navigation", "ID argument not found")
                            return@composable navController.navigate("/movies")
                        }

                        val movie = movieViewModel.readMovie(id)
                        DetailScreen(
                            movie = movie,
                            goBack = { navController.popBackStack() },
                            goToUpdate = { navController.navigate("/movies/update/${it.id}") },
                            toggleList = { movieViewModel.toggleList(it) },
                            toggleLike = { movieViewModel.toggleLike(it) },
                        )
                    }

                    composable("/movies/update/{id}") { navBackStackEntry ->
                        val id = navBackStackEntry.arguments?.getString("id")?.toLongOrNull()
                        if (id == null) {
                            Log.wtf("Navigation", "ID argument not found")
                            return@composable navController.navigate("/movies")
                        }

                        val oldMovie = movieViewModel.readMovie(id)
                        CreateUpdateScreen(
                            oldMovie = oldMovie,
                            goBack = { navController.popBackStack() },
                            saveMovieAndRedirect = { title, director, year, added, liked, critic ->
                                movieViewModel.updateMovie(
                                    oldMovie = oldMovie,
                                    title = title,
                                    director = director,
                                    year = year,
                                    added = added,
                                    liked = liked,
                                    critic = critic
                                )
                                navController.popBackStack()
                            },
                        )
                    }

                }
            }
        }
    }
}