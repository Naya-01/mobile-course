package be.vinci.exo2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import be.vinci.exo2.ui.theme.Exo2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var listNote = mutableStateListOf<Note>()
            for (item in getNote()){
                listNote.add(item)
            }
            var nb by remember { mutableStateOf(listNote.size)}
            val navController = rememberNavController()
            Exo2Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {

                        NavHost(navController = navController, startDestination = "/notes")
                        {
                            composable("/notes") {
                                NoteList(
                                    notes = listNote,
                                    action = { note -> navController.navigate("/note/${note.id}") },
                                )
                            }
                            composable("/note/{id}") { navBackStackEntry ->
                                val id = navBackStackEntry.arguments!!.getString("id")!!
                                val profile = listNote.first { it.id == id.toInt() }
                                NoteScreen(profile) { navController.popBackStack() }
                            }
                        }
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Exo2Theme {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {

            NoteList(notes = mutableListOf(), action = {})
        }
    }
}