package be.vinci.exo3

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import be.vinci.exo3.data.ToDo
import be.vinci.exo3.data.ToDoViewModel
import be.vinci.exo3.data.defaultToDos
import be.vinci.exo3.ui.screen.ToDoScreen
import be.vinci.exo3.ui.theme.Exo3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Exo3Theme {
                // A surface container using the 'background' color from the theme

                val todoViewModel = viewModel<ToDoViewModel>()
                ToDoScreen(toDoList = todoViewModel.todos, toggleImportance ={todoViewModel.toggleImportant(it)} ,
                    setDone = {it.done=true}, saveToDo ={text, important ->
                        todoViewModel.createToDo(
                            text = text,
                            important = important,
                            done = false
                        )
                    } )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Exo3Theme {
    }
}