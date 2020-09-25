package com.romain.pedepoy.movies.service

import com.romain.pedepoy.movies.data.Movie
import com.romain.pedepoy.movies.data.MovieResponse
import com.romain.pedepoy.movies.service.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface MoviesApi {
    @GET("/movies.json")
    suspend fun getMovies(): Response<List<MovieResponse>>
}