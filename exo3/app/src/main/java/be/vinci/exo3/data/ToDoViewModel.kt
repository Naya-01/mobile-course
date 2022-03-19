package be.vinci.exo3.data

import android.app.Application
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import be.vinci.exo3.data.ToDo
import kotlinx.coroutines.launch


class ToDoViewModel(application: Application) :
    AndroidViewModel(application) {
    val todos = mutableStateListOf<ToDo>()
    private val todoDAO = AppDatabase.getDatabase(application).todoDAO()

    init {
        viewModelScope.launch {
            val dbMovies = todoDAO.getAll()
            todos.addAll(dbMovies)
        }
    }

    fun readToDo(id: Long) = todos.first { it.id == id }

    fun createToDo(
        text: String,
        important: Boolean,
        done: Boolean,
    ) {

        viewModelScope.launch {
            val todo = ToDo(
                text = text,
                important = important,
                done = done,
            )
            todoDAO.insert(todo)
            todos.add(todo)
        }

    }

    fun updateToDo(
        oldTodo: ToDo,
        text: String? = null,
        important: Boolean? = null,
        done: Boolean? = null,
    ) {

        viewModelScope.launch {
            val todo = oldTodo.copy()
            text?.let { todo.text = it }
            important?.let { todo.important = it }
            done?.let { todo.done = it }

            val index = todos.indexOfFirst { it.id == todo.id }
            todoDAO.update(todo)
            todos[index] = todo // change reference to force recomposition
        }

    }

    fun deleteToDo(todo: ToDo) {
        viewModelScope.launch {
            todoDAO.delete(todo)
            todos.remove(todo)
        }

    }
    fun toggleImportant(todo: ToDo) = updateToDo(todo, important = !todo.important)

    fun toggleDone(todo: ToDo) = updateToDo(todo, done = !todo.done)

}