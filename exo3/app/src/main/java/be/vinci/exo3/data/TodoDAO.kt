package be.vinci.exo3.data

import androidx.room.*
import be.vinci.exo3.data.ToDo

@Dao
interface TodoDAO {
    @Insert
    suspend fun insert(todo: ToDo): Long
    @Delete
    suspend fun delete(todo: ToDo)
    @Update
    suspend fun update(todo: ToDo)
    @Query("select * from todo")
    suspend fun getAll(): List<ToDo>
}