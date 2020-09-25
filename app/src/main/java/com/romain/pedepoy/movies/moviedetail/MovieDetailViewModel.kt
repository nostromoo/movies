package com.romain.pedepoy.movies.moviedetail

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.romain.pedepoy.movies.R
import com.romain.pedepoy.movies.data.MoviesRepository
import javax.inject.Inject

class MovieDetailViewModel @Inject constructor(
    val  moviesRepository: MoviesRepository
) : ViewModel() {

    var id: Long = 0L

    val movie by lazy { moviesRepository.getMovie(id) }

//    fun goToPlayer(v: View) {
//        val navController = v.findNavController()
//        navController.navigate(R.id.action_movieDetailFragment_to_playerFragment)
//    }
}
