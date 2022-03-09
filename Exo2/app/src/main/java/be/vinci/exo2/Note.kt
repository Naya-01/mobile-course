package be.vinci.exo2
private const val lipsum = "\n Nulla viverra, nisi sed iaculis mollis, nulla turpis" +
        " elementum diam, a finibus turpis nisl non sapien. Pellentesque fringilla" +
        " urna et scelerisque";
data class Note (
    val id: Int,
    val title: String,
    val content: String = lipsum
    )

fun getNote(): List<Note> = listOf(
    Note(1,"One piece"),
    Note(2,"MHA"),
    Note(3,"Kingdom"),
    Note(4,"Death note"),
)