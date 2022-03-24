package be.vinci.tuto4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import be.vinci.tuto4.data.WordViewModel
import be.vinci.tuto4.ui.theme.Tuto4Theme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Tuto4Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val wordViewModel = viewModel<WordViewModel>()
                    LaunchedEffect(wordViewModel.word) {
                        delay(3000)
                        wordViewModel.refreshWord()
                    }
                    Scaffold(topBar = {
                        TopAppBar(title = { Text(text = "Discover new words") }, actions =
                        {
                            IconButton(
                                onClick = { wordViewModel.refresh() }) {
                                Icon(Icons.Default.Refresh, contentDescription = "Refresh")
                            }
                        })
                    }) {
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxSize(),
                        ) {
                            Image(
                                painter = painterResource(R.drawable.dictionary),
                                contentDescription = "Dictionary",
                                contentScale = ContentScale.Fit,
                                modifier = Modifier.weight(0.5f),
                            )
                            Text(
                                text = wordViewModel.word?.word?.capitalize(Locale.current)
                                    ?: "",
                                style = MaterialTheme.typography.h2,
                                fontFamily = FontFamily.Cursive,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.weight(0.5f).wrapContentHeight(),
                            )
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
    Tuto4Theme {
        Greeting("Android")
    }
}