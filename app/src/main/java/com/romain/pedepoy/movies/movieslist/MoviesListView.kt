package com.romain.pedepoy.movies.movieslist

import com.romain.pedepoy.movies.data.Movie

interface MoviesListView {

    fun displayMoviesList(movies: List<Movie>)
}