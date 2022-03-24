package be.vinci.tuto4.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.*
import io.ktor.client.features.get
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import kotlinx.coroutines.launch

class WordViewModel: ViewModel() {
    private val client = HttpClient(Android) {
        install(JsonFeature) { serializer = KotlinxSerializer() }
    }
    private var words = listOf<Word>()
    var word by mutableStateOf<Word?>(null)
    init { refresh() }
    fun refreshWord() { word = words.randomOrNull() }
    fun refresh() {
        viewModelScope.launch {
            words = client.get("http://10.0.2.2:3000/words")
        }
    }
}