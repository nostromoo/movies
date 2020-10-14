package com.romain.pedepoy.movies.player

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.google.android.exoplayer2.ExoPlaybackException
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.romain.pedepoy.movies.R
import com.romain.pedepoy.movies.dagger.Injectable
import kotlinx.android.synthetic.main.fragment_player.*


class PlayerFragment : Fragment(), Injectable, Player.EventListener {

    val args: PlayerFragmentArgs by navArgs()

    lateinit var player: SimpleExoPlayer

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_player, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initPlayer()
    }

    override fun onResume() {
        super.onResume()
        player.play()
    }

    override fun onPause() {
        super.onPause()
        player.pause()
    }

    override fun onDestroy() {
        super.onDestroy()
        player.release()
    }

    override fun onPlayerError(error: ExoPlaybackException) {
        super.onPlayerError(error)

        Toast.makeText(requireContext(), error.cause.toString(), Toast.LENGTH_SHORT).show()
    }

    private fun initPlayer() {

        player = SimpleExoPlayer.Builder(requireContext()).build()
        player_view.player = player
        val mediaItem: MediaItem = MediaItem.fromUri(args.videoUrl)
        player.setMediaItem(mediaItem)
        player.addListener(this)

        player.prepare()
        player.play()
    }

}