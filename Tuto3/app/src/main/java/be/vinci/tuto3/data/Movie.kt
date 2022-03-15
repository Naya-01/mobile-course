package be.vinci.tuto3.data

data class Movie(
    val id: Long = getNextId(),
    var title: String,
    var director: String,
    var year: Int,
    var added: Boolean = false,
    var liked: Boolean = false,
    var critic: String = "",
) {
    companion object {
        private var lastId = 0L
        private fun getNextId() = lastId++
    }
}

val defaultMovies = listOf(
    Movie(title = "Inception", director = "Christopher Nolan", year = 2010),
    Movie(title = "Dune", director = "Denis Villeneuve", year = 2021, liked = true, added = true),
    Movie(title = "Spirited away", director = "Hayao Miyazaki", year = 2002, liked = true),
    Movie(title = "Dragonball evolution", director = "James Wong", year = 2009, added = true),
)