package com.romain.pedepoy.movies.player

import android.graphics.PixelFormat
import android.media.MediaPlayer
import android.os.Bundle
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
import com.romain.pedepoy.movies.moviedetail.MovieDetailFragmentArgs
import javax.inject.Inject


class PlayerFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var playerViewModel: PlayerViewModel
    private lateinit var binding: FragmentPlayerBinding
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var surfaceHolder: SurfaceHolder

    val args: MovieDetailFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentPlayerBinding.inflate(inflater, container, false)

        playerViewModel = injectViewModel(viewModelFactory)
        playerViewModel.id = args.movieId

        binding.myViewModel = playerViewModel
        binding.lifecycleOwner = this

        playerViewModel.movie.observe(viewLifecycleOwner) {
            requireActivity().window.setFormat(PixelFormat.UNKNOWN)
            mediaPlayer = MediaPlayer()
            mediaPlayer.setDataSource(it.videoUrl)
            mediaPlayer.prepare()
            mediaPlayer.start()
            surfaceHolder = binding.surfaceView.holder
            mediaPlayer.setDisplay(surfaceHolder)
        }
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        if(::mediaPlayer.isInitialized){
            mediaPlayer.release()
        }
    }

}