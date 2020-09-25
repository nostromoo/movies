package com.romain.pedepoy.movies.moviedetail

import androidx.lifecycle.ViewModel
import com.romain.pedepoy.movies.data.MoviesRepository
import javax.inject.Inject

class MovieDetailViewModel @Inject constructor(
    val  moviesRepository: MoviesRepository
) : ViewModel() {

    var id: Long = 0L

    val movie by lazy { moviesRepository.getMovie(id) }

}
