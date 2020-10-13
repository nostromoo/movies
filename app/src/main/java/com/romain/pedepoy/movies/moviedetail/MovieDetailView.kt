package com.romain.pedepoy.movies.moviedetail

import com.romain.pedepoy.movies.data.Movie

interface MovieDetailView {

    fun displayPicture(movie: Movie)
    fun displaySynopsis(movie: Movie)
    fun displayReleaseLabel(movie: Movie)
    fun displayRatingLabel(movie: Movie)
    fun displayDirectorsLabel(movie: Movie)
    fun displayWritersLabel(movie: Movie)
    fun displayActorsLabel(movie: Movie)
    fun displayOfficialUrl(movie: Movie)
    fun goToPlayer(movie: Movie)
}