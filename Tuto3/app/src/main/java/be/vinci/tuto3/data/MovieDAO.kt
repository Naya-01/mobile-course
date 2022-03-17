package be.vinci.tuto3.data

import androidx.room.*

@Dao
interface MovieDAO {
    @Insert
    suspend fun insert(movie: Movie): Long
    @Delete
    suspend fun delete(movie: Movie)
    @Update
    suspend fun update(movie: Movie)
    @Query("select * from movie")
    suspend fun getAll(): List<Movie>
}