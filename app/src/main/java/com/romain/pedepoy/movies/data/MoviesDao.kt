package com.romain.pedepoy.movies.data

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MoviesDao {

    @Query("SELECT * FROM movies")
    fun getAll(): LiveData<List<Movie>>

    @Query("SELECT * FROM movies")
    fun getPagedList(): DataSource.Factory<Int, Movie>

    @Query("SELECT * FROM movies WHERE title = :title")
    fun getMovie(title: String): LiveData<Movie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies: List<Movie>)

}