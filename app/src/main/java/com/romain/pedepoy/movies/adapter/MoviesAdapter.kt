package com.romain.pedepoy.movies.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.romain.pedepoy.movies.R
import com.romain.pedepoy.movies.data.Movie
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_item.view.*

class MoviesAdapter(private val onItemClickListener: OnItemClickListener<Movie>) :
    RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    private val contents: MutableList<Movie> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = contents[position]
        if (!movie.cover.isNullOrEmpty()) {
            Picasso
                .get()
                .load(movie.cover)
                .placeholder(R.drawable.popcorn)
                .into(holder.view.picture)
        }
        holder.view.setOnClickListener { view ->
            onItemClickListener.onItemClick(view, movie)
        }
    }

    fun setItems(newList: List<Movie>) {
        contents.clear()
        contents.addAll(newList)
        notifyDataSetChanged()
    }


    class MovieViewHolder(
        val view: View
    ) : RecyclerView.ViewHolder(view)

    override fun getItemCount(): Int = contents.size
}
