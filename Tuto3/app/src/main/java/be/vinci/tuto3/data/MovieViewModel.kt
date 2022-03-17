package be.vinci.tuto3.data

import android.app.Application
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


class MovieViewModel(application: Application) :
    AndroidViewModel(application) {
    val movies = mutableStateListOf<Movie>()
    private val movieDAO = AppDatabase.getDatabase(application).movieDAO()

    init {
        viewModelScope.launch {
            val dbMovies = movieDAO.getAll()
            movies.addAll(dbMovies)
        }
    }

    fun readMovie(id: Long) = movies.first { it.id == id }

    fun createMovie(
        title: String,
        director: String,
        year: Int,
        added: Boolean,
        liked: Boolean,
        critic: String,
    ) {

        viewModelScope.launch {
            val movie = Movie(

                title = title,
                director = director,
                year = year,
                added = added,
                liked = liked,
                critic = critic
            )
            movieDAO.insert(movie)
            movies.add(movie)
        }

    }

    fun updateMovie(
        oldMovie: Movie,
        title: String? = null,
        director: String? = null,
        year: Int? = null,
        added: Boolean? = null,
        liked: Boolean? = null,
        critic: String? = null,
    ) {

        viewModelScope.launch {
            val movie = oldMovie.copy()
            title?.let { movie.title = it }
            director?.let { movie.director = it }
            year?.let { movie.year = it }
            added?.let { movie.added = it }
            liked?.let { movie.liked = it }
            critic?.let { movie.critic = it }

            val index = movies.indexOfFirst { it.id == movie.id }
            movieDAO.update(movie)
            movies[index] = movie // change reference to force recomposition
        }

    }

    fun deleteMovie(movie: Movie) {
        viewModelScope.launch {
            movieDAO.delete(movie)
            movies.remove(movie)
        }

    }
    fun toggleList(movie: Movie) = updateMovie(movie, added = !movie.added)

    fun toggleLike(movie: Movie) = updateMovie(movie, liked = !movie.liked)

}