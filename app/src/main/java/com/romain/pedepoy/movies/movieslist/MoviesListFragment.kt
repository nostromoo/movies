package com.romain.pedepoy.movies.movieslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.romain.pedepoy.movies.R
import com.romain.pedepoy.movies.adapter.MoviesAdapter
import com.romain.pedepoy.movies.adapter.OnItemClickListener
import com.romain.pedepoy.movies.dagger.Injectable
import com.romain.pedepoy.movies.data.Movie
import kotlinx.android.synthetic.main.fragment_movies_list.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class MoviesListFragment : Fragment(), Injectable, MoviesListView {

    @Inject
    lateinit var moviesListPresenter: MoviesListPresenter
    private lateinit var adapter: MoviesAdapter

    private val onItemClickListener = object : OnItemClickListener<Movie> {
        override fun onItemClick(view: View?, data: Movie) {
            val action =
                MoviesListFragmentDirections.actionMoviesListFragmentToMovieDetailFragment(data.title)
            findNavController().navigate(action)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        moviesListPresenter.moviesListView = this

        return inflater.inflate(R.layout.fragment_movies_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()

        moviesListPresenter.getMoviesList()
    }

    private fun initRecyclerView() {
        moviesList.layoutManager = LinearLayoutManager(requireContext())
        adapter = MoviesAdapter(onItemClickListener)
        moviesList.adapter = adapter
    }

    override fun displayMoviesList(movies: List<Movie>) {
        GlobalScope.launch(Dispatchers.Main) {
            adapter.setItems(movies)
        }
    }
}