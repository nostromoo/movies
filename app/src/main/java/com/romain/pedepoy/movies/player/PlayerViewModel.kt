package com.romain.pedepoy.movies.player

import androidx.lifecycle.ViewModel
import com.romain.pedepoy.movies.data.MoviesRepository
import javax.inject.Inject

class PlayerViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository
) : ViewModel() {

    var title: String = ""

    val movie by lazy { moviesRepository.getMovie(title) }

}
