package com.example.task7.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MovieDao {
    @Query("SELECT * FROM movies")
    fun getAll(): List<MovieEntity>

    @Insert
    suspend fun insertAll(vararg movies: MovieEntity)
}
