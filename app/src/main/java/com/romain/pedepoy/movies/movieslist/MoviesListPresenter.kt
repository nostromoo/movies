package com.romain.pedepoy.movies.movieslist

import com.romain.pedepoy.movies.data.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class MoviesListPresenter @Inject constructor(private val moviesRepository: MoviesRepository) {

    var moviesListView: MoviesListView? = null

    fun getMoviesList() {
        GlobalScope.launch(Dispatchers.IO) {
            moviesRepository.getMovies { movies ->
                moviesListView?.displayMoviesList(movies)
            }
        }
    }
}