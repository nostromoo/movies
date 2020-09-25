package com.romain.pedepoy.movies.movieslist

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.findNavController
import com.romain.pedepoy.movies.R
import com.romain.pedepoy.movies.data.Movie
import com.romain.pedepoy.movies.data.MoviesRepository
import com.romain.pedepoy.movies.data.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MoviesListViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository
) : ViewModel() {

    val movies = moviesRepository.movies

    fun fetchMovies() = viewModelScope.launch {
        withContext(Dispatchers.IO){
            val result = moviesRepository.fetchMovies()
            when (result.status) {
                Result.Status.SUCCESS -> {
                    result.data?.let { moviesResponse ->
                        moviesRepository.insertAll(moviesResponse.map { Movie(null, it.page.movie_title, it.heros.locale.imageurl, it.clips[0].versions.enus.sizes.sd.srcAlt) })
                        CoroutineScope(Dispatchers.Main).launch{
//                            _statusMessage.value = Event(message)
                        }
                    }
                }
                Result.Status.ERROR -> {
                    CoroutineScope(Dispatchers.Main).launch{
//                        _statusMessage.value = Event(message)
                    }
                }
                else -> {}
            }
        }
    }
}
