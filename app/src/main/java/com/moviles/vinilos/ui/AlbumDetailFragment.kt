package com.moviles.vinilos.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.moviles.vinilos.databinding.AlbumDetailBinding
import android.widget.Button
import android.widget.ImageView
import com.moviles.vinilos.viewmodels.AlbumViewModel
import com.moviles.vinilos.R
import androidx.navigation.fragment.findNavController
import com.moviles.vinilos.models.Track

class AlbumDetailFragment : Fragment() {
    private var _binding: AlbumDetailBinding? = null
    private val binding get() = _binding!!
    private var viewModel: AlbumViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = AlbumDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        viewModel = ViewModelProvider(
            activity,
            AlbumViewModel.Factory(activity.application)
        )[AlbumViewModel::class.java]
        // Observa los cambios en los álbumes
        viewModel!!.albums.observe(viewLifecycleOwner) { albumList ->
            val args: AlbumDetailFragmentArgs by navArgs()
            val albumId = args.albumId
            val album = albumList.find { it.albumId == albumId }
            album?.let {
                binding.album = it
                binding.albumReleaseYear.text = it.releaseDate.substring(0, 4)
            }
            // Esto es solo para probar que funciona
            //if (album != null) {
            //    viewModel!!.addTrackToAlbum(
            //        album.albumId, "track 1", "3:00")
            // }
        }
        val volverButton = view.findViewById<Button>(R.id.volverButton)
        volverButton.setOnClickListener {
            findNavController().navigate(R.id.action_albumDetailFragment_to_albumFragment)
        }
        val volverIcon = view.findViewById<ImageView>(R.id.backIcon)
        volverIcon.setOnClickListener {
            findNavController().navigate(R.id.action_albumDetailFragment_to_albumFragment)
        }
        val verTracksButton = view.findViewById<Button>(R.id.verTracks)
        verTracksButton.setOnClickListener {
            val args: AlbumDetailFragmentArgs by navArgs()
            val albumId = args.albumId
            val action = AlbumDetailFragmentDirections.actionAlbumDetailFragmentToTrackFragment(albumId)
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}