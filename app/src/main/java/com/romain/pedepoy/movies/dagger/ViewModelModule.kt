package com.romain.pedepoy.movies.dagger

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.romain.pedepoy.movies.moviedetail.MovieDetailViewModel
import com.romain.pedepoy.movies.movieslist.MoviesListViewModel
import com.romain.pedepoy.movies.viewmodels.ViewModelsFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MoviesListViewModel::class)
    abstract fun bindMoviesListViewModel(viewModel: MoviesListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MovieDetailViewModel::class)
    abstract fun bindMovieDetailViewModel(viewModel: MovieDetailViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelsFactory): ViewModelProvider.Factory

}
