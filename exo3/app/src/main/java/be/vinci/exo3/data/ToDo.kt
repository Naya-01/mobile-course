package be.vinci.exo3.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ToDo(
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    var text: String,
    var important: Boolean = false,
    var done: Boolean = false,
) {
    companion object {
        private var lastId = 0L
        private fun getNextId() = lastId++
    }
}

val defaultToDos = listOf(
    ToDo(text = "Finir la fiche 3", important = true, done = false ),
    ToDo(text = "Finir la fiche 4", important = false, done = false ),
    ToDo(text = "Finir le projet", important = true, done = false ),
    ToDo(text = "Finir le projet", important = true, done = true ),
)