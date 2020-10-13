package com.romain.pedepoy.movies.player

import com.romain.pedepoy.movies.MvpView
import com.romain.pedepoy.movies.data.Movie

interface PlayerView: MvpView {
    fun initVideo(movie: Movie)
}