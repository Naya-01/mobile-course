package be.vinci.listeproject

import android.os.Bundle
import android.widget.ScrollView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.EmojiPeople
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import be.vinci.listeproject.ui.theme.ListeProjectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ListeProjectTheme {
                var listName by remember {
                    mutableStateOf(
                        listOf<String>(
                            "Baroni",
                            "Rayan",
                            "Stefan",
                            "Nico",
                            "Stefan",
                            "Baroni",
                            "Rayan",
                            "Stefan",
                            "Nico",
                            "Stefan",
                            "Baroni",
                            "Rayan",
                            "Stefan",
                            "Nico",
                            "Stefan",
                        )
                    )
                }
                var name by remember { mutableStateOf("") }
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {

                        Greetings(listName)
                        Spacer(modifier = Modifier.height(64.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(8.dp)
                        ) {
                            EditName(
                                name,
                                changeName = { newName ->
                                    name = newName
                                }) { if (name != "") listName = listName + listOf<String>(name) }
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun Greetings(names: List<String>) {
    Column(
        Modifier
            .background(Color(0xFFEDF3F2))
            .padding(vertical = 4.dp)
            .size(200.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
//        items(items = names) { name ->
//            Greeting(name = name)
//        }

        for (item in names){
            Greeting(name = item)
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
fun EditName(oldName: String, changeName: (String) -> Unit, updateList: () -> Unit) {
    Column {
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp),
        ) {
            OutlinedTextField(
                value = oldName,
                onValueChange = changeName,
                label = { Text(text = "New name") }
            )
            IconButton(onClick = { updateList() }) {
                Icon(
                    imageVector = Icons.Default.AddCircle,
                    contentDescription = null,
                )
            }
        }


    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ListeProjectTheme {
        Column() {
            Greetings(
                listOf<String>(
                    "rayan",
                    "rayan",
                    "rayan",
                    "rayan",
                    "rayan",
                    "rayan",
                    "rayan",
                    "rayan",
                    "rayan",
                    "rayan"
                )
            )
            Spacer(modifier = Modifier.height(64.dp))
            EditName("rayan", {}) {}
        }
    }
}