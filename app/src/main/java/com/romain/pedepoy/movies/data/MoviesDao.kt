package com.romain.pedepoy.movies.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MoviesDao {

    @Query("SELECT * FROM movies")
    fun getMoviesList(): List<Movie>

    @Query("SELECT * FROM movies WHERE title = :title")
    fun getMovie(title: String): Movie

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies: List<Movie>)

}