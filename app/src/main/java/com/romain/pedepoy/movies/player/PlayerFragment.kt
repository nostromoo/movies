package com.romain.pedepoy.movies.player

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.romain.pedepoy.movies.dagger.Injectable
import com.romain.pedepoy.movies.dagger.injectViewModel
import com.romain.pedepoy.movies.databinding.FragmentPlayerBinding
import javax.inject.Inject

class PlayerFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var playerViewModel: PlayerViewModel
    private lateinit var binding: FragmentPlayerBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentPlayerBinding.inflate(inflater, container, false)

        playerViewModel = injectViewModel(viewModelFactory)

        binding.myViewModel = playerViewModel
        binding.lifecycleOwner = this
        return binding.root
    }

}