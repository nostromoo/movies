package com.romain.pedepoy.movies.player

import android.graphics.PixelFormat
import android.media.MediaPlayer
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.SurfaceHolder
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.romain.pedepoy.movies.R
import com.romain.pedepoy.movies.dagger.Injectable
import com.romain.pedepoy.movies.data.Movie
import kotlinx.android.synthetic.main.fragment_player.*
import javax.inject.Inject


class PlayerFragment : Fragment(), Injectable, SurfaceHolder.Callback, PlayerView {

    private var mediaPlayer: MediaPlayer? = null
    private lateinit var surfaceHolder: SurfaceHolder

    val args: PlayerFragmentArgs by navArgs()

    @Inject
    lateinit var presenter: PlayerPresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        presenter.playerView = this

        return inflater.inflate(R.layout.fragment_player, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initPlayer()
        presenter.initVideo(args.movieTitle)
    }

    override fun onPause() {
        super.onPause()
        mediaPlayer?.pause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.release()
    }

    override fun surfaceChanged(holder: SurfaceHolder?, format: Int, width: Int, height: Int) {
    }

    override fun surfaceDestroyed(holder: SurfaceHolder?) {
    }

    override fun surfaceCreated(holder: SurfaceHolder?) {
        mediaPlayer?.start()
        mediaPlayer?.setDisplay(surfaceHolder)
    }

    override fun initVideo(movie: Movie) {
        val displayMetrics = DisplayMetrics()
        requireActivity().windowManager.defaultDisplay.getMetrics(displayMetrics)
        val width = displayMetrics.widthPixels
        surfaceHolder.setFixedSize(width, width * movie.videoHeight / movie.videoWidth)
    }

    private fun initPlayer() {
        requireActivity().window.setFormat(PixelFormat.UNKNOWN)
        surfaceHolder = surfaceView.holder
        surfaceHolder.addCallback(this@PlayerFragment)
        mediaPlayer = MediaPlayer()
        mediaPlayer?.setDataSource(args.videoUrl)
        mediaPlayer?.prepare()
    }
}