package com.romain.pedepoy.movies.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.romain.pedepoy.movies.data.MoviesRepository
import com.romain.pedepoy.movies.movieslist.MoviesListViewModel
import com.romain.pedepoy.movies.player.PlayerViewModel
import javax.inject.Inject

class ViewModelsFactory @Inject constructor(private val repository: MoviesRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MoviesListViewModel::class.java) -> {
                MoviesListViewModel(repository) as T
            }
//            modelClass.isAssignableFrom(MovieDetailViewModel::class.java) -> {
//                MovieDetailViewModel(repository) as T
//            }
            modelClass.isAssignableFrom(PlayerViewModel::class.java) -> {
                PlayerViewModel(repository) as T
            }
            else -> throw IllegalArgumentException("Unknown View Model class")
        }
    }

}