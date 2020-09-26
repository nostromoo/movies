package com.romain.pedepoy.movies.service

import com.romain.pedepoy.movies.data.MovieResponse
import retrofit2.Response
import retrofit2.http.GET


interface MoviesApi {
    @GET("/movies.json")
    suspend fun getMovies(): Response<List<MovieResponse>>
}