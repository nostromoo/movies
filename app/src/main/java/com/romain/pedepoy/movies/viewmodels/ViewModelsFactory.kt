package com.romain.pedepoy.movies.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.romain.pedepoy.movies.data.MoviesRepository
import com.romain.pedepoy.movies.moviedetail.MovieDetailViewModel
import com.romain.pedepoy.movies.movieslist.MoviesListViewModel
import java.lang.IllegalArgumentException
import javax.inject.Inject

class ViewModelsFactory @Inject constructor(private val repository: MoviesRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MoviesListViewModel::class.java)){
            return MoviesListViewModel(
                repository
            ) as T
        } else if(modelClass.isAssignableFrom(MovieDetailViewModel::class.java)){
            return MovieDetailViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown View Model class")
    }

}