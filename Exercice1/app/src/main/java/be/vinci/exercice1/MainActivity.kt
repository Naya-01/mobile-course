package be.vinci.exercice1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.TouchApp
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import be.vinci.exercice1.ui.theme.Exercice1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { //point d'entree du framework
            var valeur by remember { mutableStateOf(0) }
            Exercice1Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column(
                    ) {
                        printClick(valeur)
                        myButton(){valeur++}

                    }
                }
            }
        }
    }
}

@Composable
fun printClick(valeur: Int) {
    Surface(shape = MaterialTheme.shapes.medium, elevation = 3.dp) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp),
        ) {
            Text(
                text = "$valeur clicks",
                style = MaterialTheme.typography.h6,
            )
            Spacer(modifier = Modifier.width(4.dp))
            Icon(
                imageVector = Icons.Default.TouchApp,
                contentDescription = null,
            )
        }
    }
}

@Composable
fun myButton(updateEntier: () -> Unit){
    Button(onClick = { updateEntier()}, modifier = Modifier.padding(8.dp)) {
        Text(text = "Increase your clicks")
        Icon(
            imageVector = Icons.Default.TrendingUp,
            contentDescription = null,
            modifier = Modifier.padding(start = 4.dp)
        )

    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Exercice1Theme {
        Column() {
            printClick(0)
            myButton(){0}
        }
    }
}