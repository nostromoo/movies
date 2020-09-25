package com.romain.pedepoy.movies.data

import javax.inject.Inject
import javax.inject.Singleton
import androidx.paging.toLiveData

@Singleton
class MoviesRepository  @Inject constructor(private val dao: MoviesDao,
                                            private val remoteSource: MoviesRemoteDataSource) {

    val movies = dao.getPagedList().toLiveData(pageSize = 50)

    fun getMovie(id: Long) = dao.getMovie(id)


    suspend fun fetchMovies(): Result<List<MovieResponse>> {
        return remoteSource.fetchData()
    }

    suspend fun insertAll(movies: List<Movie>){
        return dao.insertAll(movies)
    }

}