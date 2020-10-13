package com.romain.pedepoy.movies.moviedetail

import com.romain.pedepoy.movies.data.Movie

interface MovieDetailView {

    fun displayMoviesInfo(movie: Movie)
    fun goToPlayer(movie: Movie)
}