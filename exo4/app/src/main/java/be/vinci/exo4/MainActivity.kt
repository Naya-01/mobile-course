package be.vinci.exo4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import be.vinci.exo4.data.JokeViewModel
import be.vinci.exo4.ui.screen.JokeScreen
import be.vinci.exo4.ui.screen.MainScreen
import be.vinci.exo4.ui.theme.Exo4Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val jokeViewModel = viewModel<JokeViewModel>()
            val navController = rememberNavController()
            Exo4Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
//                    MainScreen()
//                    JokeScreen(jokeViewModel = jokeViewModel, category = "christmas") {}
                    NavHost(navController = navController, startDestination = "/Home")
                    {
                        composable("/Home") {
                            MainScreen(jokeViewModel){navController.navigate("/joke/${it}")}
                        }
                        composable("/joke/{category}") { navBackStackEntry ->
                            val category = navBackStackEntry.arguments!!.getString("category")!!
                            JokeScreen(jokeViewModel = jokeViewModel, category = category)
                            {navController.popBackStack()}

                        }
                    }

                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Exo4Theme {
        Greeting("Android")
    }
}