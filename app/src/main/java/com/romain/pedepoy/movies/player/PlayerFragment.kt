package com.romain.pedepoy.movies.player

import android.graphics.PixelFormat
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.SurfaceHolder
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.navArgs
import com.romain.pedepoy.movies.dagger.Injectable
import com.romain.pedepoy.movies.dagger.injectViewModel
import com.romain.pedepoy.movies.databinding.FragmentPlayerBinding
import javax.inject.Inject


class PlayerFragment : Fragment(), Injectable, SurfaceHolder.Callback {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var playerViewModel: PlayerViewModel
    private lateinit var binding: FragmentPlayerBinding
    private var mediaPlayer: MediaPlayer? = null
    private lateinit var surfaceHolder: SurfaceHolder

    val args: PlayerFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentPlayerBinding.inflate(inflater, container, false)

        playerViewModel = injectViewModel(viewModelFactory)
        playerViewModel.title = args.movieTitle

        binding.myViewModel = playerViewModel
        binding.lifecycleOwner = this

        requireActivity().window.setFormat(PixelFormat.UNKNOWN)
        surfaceHolder = binding.surfaceView.holder
        surfaceHolder.addCallback(this@PlayerFragment)
        mediaPlayer = MediaPlayer()
        mediaPlayer?.setDataSource(args.videoUrl)
        mediaPlayer?.prepare()

        playerViewModel.movie.observe(viewLifecycleOwner) {
        }
        return binding.root
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
        Log.d("rominou", "surfaceChanged")
    }

    override fun surfaceDestroyed(holder: SurfaceHolder?) {
        Log.d("rominou", "surfaceDestroyed")
    }

    override fun surfaceCreated(holder: SurfaceHolder?) {
        Log.d("rominou", "surfaceCreated")
        mediaPlayer?.start()
        mediaPlayer?.setDisplay(surfaceHolder)
    }

}