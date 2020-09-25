package com.romain.pedepoy.movies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.romain.pedepoy.movies.data.Movie
import com.romain.pedepoy.movies.databinding.MovieItemBinding
import com.romain.pedepoy.movies.movieslist.MoviesListFragment
import java.lang.ref.WeakReference

class MoviesAdapter(fragment: MoviesListFragment) :
    PagedListAdapter<Movie, MoviesAdapter.ViewHolder>(
        MovieDiffCallback()
    ) {

    private val fragment = WeakReference(fragment)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false), fragment)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = getItem(position)
        movie?.let {
            holder.bind(movie)
        }
    }

    class ViewHolder(private val binding: MovieItemBinding,
                     private val fragment: WeakReference<MoviesListFragment>
    ) : RecyclerView.ViewHolder(binding.root){
        fun bind(movie: Movie) {
            binding.movie = movie
            binding.view = fragment.get()
        }
    }
}

private class MovieDiffCallback : DiffUtil.ItemCallback<Movie>() {

    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }
}