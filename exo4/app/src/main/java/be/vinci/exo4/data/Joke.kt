package be.vinci.exo4.data

import kotlinx.serialization.Serializable
import androidx.room.PrimaryKey

@Serializable
data class Joke(
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    val question: String,
    val answer: String,
    val category: String
)