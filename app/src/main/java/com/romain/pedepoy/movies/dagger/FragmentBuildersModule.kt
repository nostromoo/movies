package com.romain.pedepoy.movies.dagger


import com.romain.pedepoy.movies.moviedetail.MovieDetailFragment
import com.romain.pedepoy.movies.movieslist.MoviesListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeMoviesListFragment(): MoviesListFragment

    @ContributesAndroidInjector
    abstract fun contributeMovieDetailFragment(): MovieDetailFragment

}
