package be.vinci.exo4.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import be.vinci.exo4.R
import be.vinci.exo4.data.Joke
import be.vinci.exo4.data.JokeViewModel

@Composable
fun MainScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Discover new jokes") },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
            )
        }
    ){
        val jokeViewModel = viewModel<JokeViewModel>()
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
        ) {
            Image(
                painter = painterResource(R.drawable.christmas),
                contentDescription = "Dictionary",
                contentScale = ContentScale.Fit,
                modifier = Modifier.weight(0.5f)
                    .clickable {  },
            )
            Image(
                painter = painterResource(R.drawable.pun),
                contentDescription = "Dictionary",
                contentScale = ContentScale.Fit,
                modifier = Modifier.weight(0.5f),
            )
            Image(
                painter = painterResource(R.drawable.programming),
                contentDescription = "Dictionary",
                contentScale = ContentScale.Fit,
                modifier = Modifier.weight(0.5f),
            )

        }



    }





}
