package be.vinci.exo4.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import be.vinci.exo4.data.JokeViewModel
import kotlinx.coroutines.delay



@Composable
fun JokeScreen(jokeViewModel: JokeViewModel, category: String, back: () -> Unit) {
    var ok by  remember { mutableStateOf(false) }
    LaunchedEffect(true) {
        jokeViewModel.refreshJoke(category = category)
        delay(5000)
        ok=true;
    }
    Scaffold(topBar = {
        TopAppBar(title = { Text(text = "A joke of $category ") },
            navigationIcon = {
                if(ok){
                    IconButton(onClick = { back() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            }
        )
    }) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
        ) {

            Text(

                text = jokeViewModel.joke?.question?.capitalize(Locale.current)
                    ?: "",
                style = MaterialTheme.typography.h2,
                fontFamily = FontFamily.Cursive,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .weight(0.5f)
                    .wrapContentHeight(),
            )
            if(ok){
                AnswerPopUp(jokeViewModel = jokeViewModel)
            }
        }
    }


}

@Composable
fun AnswerPopUp(jokeViewModel: JokeViewModel){
    Text(

        text = jokeViewModel.joke?.answer?.capitalize(Locale.current)
            ?: "",
        style = MaterialTheme.typography.h2,
        fontFamily = FontFamily.Cursive,
        textAlign = TextAlign.Center,
    )
}