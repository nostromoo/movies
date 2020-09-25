package com.romain.pedepoy.movies.moviedetail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.navArgs
import com.romain.pedepoy.movies.dagger.Injectable
import com.romain.pedepoy.movies.dagger.injectViewModel
import com.romain.pedepoy.movies.databinding.FragmentMovieDetailBinding
import javax.inject.Inject

class MovieDetailFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var productlistViewModel: MovieDetailViewModel
    private lateinit var binding: FragmentMovieDetailBinding

    val args: MovieDetailFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentMovieDetailBinding.inflate(inflater, container, false)

        productlistViewModel = injectViewModel(viewModelFactory)
        productlistViewModel.id = args.movieId
        binding.viewModel = productlistViewModel
        binding.lifecycleOwner = this

        productlistViewModel.movie.observe(viewLifecycleOwner) {
        }
        return binding.root
    }

}