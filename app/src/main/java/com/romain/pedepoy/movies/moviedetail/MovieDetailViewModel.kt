package com.romain.pedepoy.movies.moviedetail

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.lifecycle.ViewModel
import com.romain.pedepoy.movies.data.MoviesRepository
import javax.inject.Inject

class MovieDetailViewModel @Inject constructor(
    val  moviesRepository: MoviesRepository
) : ViewModel() {

    var title: String = ""

    val movie by lazy { moviesRepository.getMovie(title) }

    fun goToOfficialSite(context: Context) {
        if(movie.value?.officialUrl.isNullOrEmpty().not()) {
            val builderCustomTabs: CustomTabsIntent.Builder = CustomTabsIntent.Builder()
            val intentCustomTabs: CustomTabsIntent = builderCustomTabs.build()
            intentCustomTabs.intent.setPackage("com.android.chrome")
            intentCustomTabs.intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intentCustomTabs.launchUrl(context, Uri.parse(movie.value?.officialUrl))
        }
    }
}
