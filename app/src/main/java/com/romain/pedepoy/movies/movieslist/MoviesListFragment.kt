package com.romain.pedepoy.movies.movieslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.romain.pedepoy.movies.R
import com.romain.pedepoy.movies.adapter.MoviesAdapter
import com.romain.pedepoy.movies.dagger.Injectable
import com.romain.pedepoy.movies.dagger.injectViewModel
import com.romain.pedepoy.movies.databinding.FragmentMoviesListBinding
import javax.inject.Inject

class MoviesListFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var moviesListViewModel: MoviesListViewModel
    private lateinit var adapter: MoviesAdapter
    private lateinit var binding: FragmentMoviesListBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentMoviesListBinding.inflate(inflater, container, false)

        moviesListViewModel = injectViewModel(viewModelFactory)

        binding.myViewModel = moviesListViewModel
        binding.lifecycleOwner = this
        initRecyclerView()
        moviesListViewModel.fetchMovies()

        return binding.root
    }

    private fun initRecyclerView(){
        binding.productList.layoutManager = LinearLayoutManager(requireContext())
        adapter = MoviesAdapter(this)
        binding.productList.adapter = adapter
        displayProductsList()
    }

    private fun displayProductsList(){
        moviesListViewModel.movies.observe(viewLifecycleOwner){
            adapter.submitList(it)
            adapter.notifyDataSetChanged()
        }
    }

    fun goToMovieDetail(v: View, id: Long) {
        val action = MoviesListFragmentDirections.actionMoviesListFragmentToMovieDetailFragment(id)
        v.findNavController().navigate(action)
    }
}