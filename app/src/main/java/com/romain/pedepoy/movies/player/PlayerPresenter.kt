package com.romain.pedepoy.movies.player

import com.romain.pedepoy.movies.BasePresenter
import com.romain.pedepoy.movies.data.Movie
import com.romain.pedepoy.movies.data.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PlayerPresenter @Inject constructor(private val moviesRepository: MoviesRepository) :
    BasePresenter<PlayerView>() {

    var playerView: PlayerView? = null

    var movie: Movie? = null

    fun initVideo(title: String) {
        GlobalScope.launch {
            withContext(Dispatchers.IO) {
                movie = moviesRepository.getMovie(title)
                movie?.let { playerView?.initVideo(it) }
            }
        }
    }
}