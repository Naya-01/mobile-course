package be.vinci.tuto1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EmojiPeople
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import be.vinci.tuto1.ui.theme.Tuto1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Tuto1Theme {
                var name by remember { mutableStateOf("Android") }
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Greeting(name)
                        Spacer(modifier = Modifier.height(64.dp))
                        EditName(name) { newName -> name = newName }
                        // or { name = it }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Surface(shape = MaterialTheme.shapes.medium, elevation = 3.dp) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp),
        ) {
            Text(
                text = "Hello $name!",
                style = MaterialTheme.typography.h6,
            )
            Spacer(modifier = Modifier.width(4.dp))
            Icon(
                imageVector = Icons.Default.EmojiPeople,
                contentDescription = "Waving person",
            )
        }
    }
}

@Composable
fun EditName(oldName: String, changeName: (String) -> Unit) {
    Column {
        Text(
            text = "Change your name:",
            style = MaterialTheme.typography.subtitle1
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = oldName,
            onValueChange = changeName,
            label = { Text(text = "Name") }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Tuto1Theme {
        Greeting("Android")
        Spacer(modifier = Modifier.height(64.dp))
        EditName(oldName = "mon test") { println(it) }
    }
}