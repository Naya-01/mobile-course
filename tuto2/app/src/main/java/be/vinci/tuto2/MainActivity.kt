package be.vinci.tuto2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import be.vinci.tuto2.ui.theme.Tuto2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val profiles = getProfiles()
            Tuto2Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    NavHost(navController = navController, startDestination = "/profiles")
                    {
                        composable("/profiles") {
                            ProfileList(profiles) { profile ->
                                navController.navigate("/profile/${profile.id}")
                            }
                        }
                        composable("/profile/{id}") { navBackStackEntry ->
                            val id = navBackStackEntry.arguments!!.getString("id")!!
                            val profile = profiles.first { it.id == id.toInt() }
                            ProfileScreen(profile) { navController.popBackStack() }
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
    Tuto2Theme {
        Column() {
            Greeting("Android")
        }
    }
}