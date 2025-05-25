package com.moviles.vinilos.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.moviles.vinilos.R
import com.moviles.vinilos.databinding.AlbumTracksBinding
import com.moviles.vinilos.viewmodels.AlbumViewModel
import com.moviles.vinilos.ui.adapters.TracksAdapter


class AlbumTracksFragment: Fragment() {
    private var _binding: AlbumTracksBinding? = null
    private val binding get() = _binding!!
    private var viewModel: AlbumViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = AlbumTracksBinding.inflate(inflater, container, false)
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
                binding.tracksRV.adapter = TracksAdapter(it.tracks)
                binding.tracksRV.layoutManager = LinearLayoutManager(context)
            }
        }
        val volverButton = view.findViewById<Button>(R.id.volverButton)
        volverButton.setOnClickListener {
            val args: AlbumTracksFragmentArgs by navArgs()
            val albumId = args.albumId
            val action = AlbumTracksFragmentDirections.actionAlbumTracksFragmentToAlbumDetailFragment(albumId)
            findNavController().navigate(action)

        }
        val volverIcon = view.findViewById<ImageView>(R.id.backIcon)
        volverIcon.setOnClickListener {
            val args: AlbumTracksFragmentArgs by navArgs()
            val albumId = args.albumId
            val action = AlbumTracksFragmentDirections.actionAlbumTracksFragmentToAlbumDetailFragment(albumId)
            findNavController().navigate(action)
        }
        val agregarTracksButton = view.findViewById<Button>(R.id.agregarTrack)
        agregarTracksButton.setOnClickListener {
            val args: AlbumTracksFragmentArgs by navArgs()
            val albumId = args.albumId
            val nombreAlbum = binding.album?.name ?: "Album"
            val action = AlbumTracksFragmentDirections.actionAlbumTracksFragmentToCreateTrackFragment(albumId, nombreAlbum)
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}