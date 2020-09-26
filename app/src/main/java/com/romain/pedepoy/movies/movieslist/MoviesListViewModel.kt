package com.romain.pedepoy.movies.movieslist

import androidx.lifecycle.ViewModel
import com.romain.pedepoy.movies.data.MoviesRepository
import javax.inject.Inject

class MoviesListViewModel @Inject constructor(
    moviesRepository: MoviesRepository
) : ViewModel() {

    val movies = moviesRepository.movies

}
