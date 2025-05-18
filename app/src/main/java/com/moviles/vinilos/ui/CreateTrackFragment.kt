package com.moviles.vinilos.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.moviles.vinilos.databinding.CreateTrackBinding
import com.moviles.vinilos.viewmodels.AlbumViewModel
import com.moviles.vinilos.R
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import android.widget.TextView



class CreateTrackFragment: Fragment() {
    private var _binding: CreateTrackBinding? = null
    private val binding get() = _binding!!
    private var viewModel: AlbumViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = CreateTrackBinding.inflate(inflater, container, false)
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

        val args: CreateTrackFragmentArgs by navArgs()
        val albumId = args.albumId
        val albumName = args.albumName

        val guardarButton = view.findViewById<Button>(R.id.agregarTrack)
        guardarButton.setOnClickListener {
            viewLifecycleOwner.lifecycleScope.launch{
                val nombreTrack = view.findViewById<com.google.android.material.textfield.TextInputEditText>(R.id.nameTrack).text.toString()
                val duracionTrack = view.findViewById<com.google.android.material.textfield.TextInputEditText>(R.id.trackDuration).text.toString()


                if(nombreTrack.isNotBlank() && duracionTrack.isNotBlank()){
                    try{
                        viewModel?.addTrackToAlbum(albumId, nombreTrack, duracionTrack)
                        Toast.makeText(requireContext(), "Álbum creado correctamente", Toast.LENGTH_SHORT).show()
                        val action = CreateTrackFragmentDirections.actionCreateTrackFragmentToAlbumTracksFragment(albumId)
                        findNavController().navigate(action)
                    } catch (e: Exception){
                        Toast.makeText(requireContext(), "Error creando álbum", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(requireContext(), "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
                }
            }
        }

        val formTitle = view.findViewById<TextView>(R.id.createTrackTitle)
        formTitle.text = getString(R.string.title_new_track, albumName)

        val cancelButton = view.findViewById<Button>(R.id.btnCancelar)
        cancelButton.setOnClickListener {
            val action = CreateTrackFragmentDirections.actionCreateTrackFragmentToAlbumTracksFragment(albumId)
            findNavController().navigate(action)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}