package com.romain.pedepoy.movies.data

import com.romain.pedepoy.movies.service.BaseDataSource
import com.romain.pedepoy.movies.service.MoviesApi
import javax.inject.Inject


class MoviesRemoteDataSource @Inject constructor(private val service: MoviesApi): BaseDataSource()  {

    suspend fun fetchData(): Result<List<MovieResponse>> = getResult { service.getMovies() }
}
