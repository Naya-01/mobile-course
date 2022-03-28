package be.vinci.exo4.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import kotlinx.coroutines.launch

class JokeViewModel : ViewModel() {
    private val client = HttpClient(Android) {
        install(JsonFeature) { serializer = KotlinxSerializer() }
    }
    private var jokes = listOf<Joke>()
    var joke by mutableStateOf<Joke?>(null)

    init {
        refresh()
    }

    fun refreshJoke(category: String) {
         joke = jokes.filter { joke -> joke.category == category }.randomOrNull()
    }

    fun refresh() {
        viewModelScope.launch {
            jokes = client.get("http://10.0.2.2:3000/jokes")
        }
    }
}