package com.romain.pedepoy.movies.player

import com.romain.pedepoy.movies.data.Movie

interface PlayerView {
    fun initVideo(movie: Movie)
}