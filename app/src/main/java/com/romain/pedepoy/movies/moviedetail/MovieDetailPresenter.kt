package com.romain.pedepoy.movies.moviedetail

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import com.romain.pedepoy.movies.data.Movie
import com.romain.pedepoy.movies.data.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieDetailPresenter @Inject constructor(private val moviesRepository: MoviesRepository) {

    var movieDetailView: MovieDetailView? = null

    var movie: Movie? = null

    fun getMovie(title: String) {
        GlobalScope.launch {
            withContext(Dispatchers.IO) {
                movie = moviesRepository.getMovie(title)
                movie?.let {
                    withContext(Dispatchers.Main) {
                        movieDetailView?.displayPicture(it)
                        movieDetailView?.displaySynopsis(it)
                        movieDetailView?.displayReleaseLabel(it)
                        movieDetailView?.displayRatingLabel(it)
                        movieDetailView?.displayDirectorsLabel(it)
                        movieDetailView?.displayWritersLabel(it)
                        movieDetailView?.displayActorsLabel(it)
                        movieDetailView?.displayOfficialUrl(it)
                    }
                }
            }
        }
    }

    fun goToOfficialSite(context: Context) {
        if (!movie?.officialUrl.isNullOrEmpty()) {
            val builderCustomTabs: CustomTabsIntent.Builder = CustomTabsIntent.Builder()
            val intentCustomTabs: CustomTabsIntent = builderCustomTabs.build()
            intentCustomTabs.intent.setPackage("com.android.chrome")
            intentCustomTabs.intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intentCustomTabs.launchUrl(context, Uri.parse(movie?.officialUrl))
        }
    }

    fun goToPlayer() {
        movie?.let { movieDetailView?.goToPlayer(it) }
    }
}