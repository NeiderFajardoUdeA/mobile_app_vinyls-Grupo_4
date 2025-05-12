package com.moviles.vinilos.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.moviles.vinilos.databinding.ArtistDetailBinding
import android.widget.Button
import android.widget.ImageView
import com.moviles.vinilos.viewmodels.ArtistViewModel
import com.moviles.vinilos.R
import androidx.navigation.fragment.findNavController

class ArtistDetailFragment : Fragment() {
    private var _binding: ArtistDetailBinding? = null
    private val binding get() = _binding!!
    private var viewModel: ArtistViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ArtistDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        viewModel = ViewModelProvider(activity, ArtistViewModel.Factory(activity.application))[ArtistViewModel::class.java]
        // Observa los cambios en los artistas
        viewModel!!.artists.observe(viewLifecycleOwner) { artistList ->
            val args: ArtistDetailFragmentArgs by navArgs()
            val artistId = args.artistId
            val album = artistList.find { it.artistId == artistId }
            album?.let {
                binding.artist = it
                binding.artistBirthYear.text = it.birthDate.substring(0, 4)
            }
        }
        val volverButton = view.findViewById<Button>(R.id.volverButton)
        volverButton.setOnClickListener {
            findNavController().navigate(R.id.action_artistDetailFragment_to_artistFragment)
        }
        val volverIcon = view.findViewById<ImageView>(R.id.backIcon)
        volverIcon.setOnClickListener {
            findNavController().navigate(R.id.action_artistDetailFragment_to_artistFragment)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}