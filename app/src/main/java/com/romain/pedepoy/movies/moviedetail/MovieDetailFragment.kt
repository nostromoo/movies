package com.romain.pedepoy.movies.moviedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.romain.pedepoy.movies.R
import com.romain.pedepoy.movies.dagger.Injectable
import com.romain.pedepoy.movies.data.Movie
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_movie_detail.*
import javax.inject.Inject

class MovieDetailFragment : Fragment(), MovieDetailView, Injectable {

    val args: MovieDetailFragmentArgs by navArgs()

    @Inject
    lateinit var presenter: MovieDetailPresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        presenter.movieDetailView = this

        presenter.getMovie(args.movieTitle)
        return inflater.inflate(R.layout.fragment_movie_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        officialSite.setOnClickListener {
            presenter.goToOfficialSite(requireContext())
        }

        playButton.setOnClickListener {
            presenter.goToPlayer()
        }
    }

    override fun displayMoviesInfo(movie: Movie) {
        if (!movie.cover.isNullOrEmpty()) {
            Picasso
                .get()
                .load(movie.cover)
                .placeholder(R.drawable.popcorn)
                .into(picture)
        }
        synopsis.text = movie.synopsis
        releaseDate.text = movie.releaseLabel()
        rating.text = movie.ratingLabel()
        directors.text = movie.directorsLabel()
        writers.text = movie.writersLabel()
        actors.text = movie.actorsLabel()
        officialSite.text = movie.officialUrl
    }

    override fun goToPlayer(movie: Movie) {
        movie.videoUrl?.let { videoUrl ->
            val action = MovieDetailFragmentDirections.actionMovieDetailFragmentToPlayerFragment(
                movie.title,
                videoUrl
            )
            findNavController().navigate(action)
        }
    }
}