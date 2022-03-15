package be.vinci.tuto3.data

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class MovieViewModel : ViewModel() {

    val movies = mutableStateListOf(*defaultMovies.toTypedArray())

    fun readMovie(id: Long) = movies.first { it.id == id }

    fun createMovie(
        title: String,
        director: String,
        year: Int,
        added: Boolean,
        liked: Boolean,
        critic: String,
    ) {
        val movie = Movie(
            title = title,
            director = director,
            year = year,
            added = added,
            liked = liked,
            critic = critic
        )
        movies.add(movie)
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
        val movie = oldMovie.copy()
        title?.let { movie.title = it }
        director?.let { movie.director = it }
        year?.let { movie.year = it }
        added?.let { movie.added = it }
        liked?.let { movie.liked = it }
        critic?.let { movie.critic = it }

        val index = movies.indexOfFirst { it.id == movie.id }
        movies[index] = movie // change reference to force recomposition
    }

    fun deleteMovie(movie: Movie) {
        movies.remove(movie)
    }

    fun toggleList(movie: Movie) = updateMovie(movie, added = !movie.added)

    fun toggleLike(movie: Movie) = updateMovie(movie, liked = !movie.liked)

}