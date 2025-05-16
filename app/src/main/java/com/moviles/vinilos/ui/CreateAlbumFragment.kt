package com.moviles.vinilos.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.moviles.vinilos.R
import com.moviles.vinilos.databinding.CreateAlbumBinding
import com.moviles.vinilos.viewmodels.AlbumViewModel

class CreateAlbumFragment: Fragment() {
    private var _binding: CreateAlbumBinding? = null
    private val binding get() = _binding!!
    private var viewModel: AlbumViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = CreateAlbumBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        viewModel = ViewModelProvider(activity, AlbumViewModel.Factory(activity.application))[AlbumViewModel::class.java]

        binding.btnSubmit.setOnClickListener {
            val postParams = mapOf(
                "name" to binding.nameAlbum.text.toString(),
                "cover" to binding.coverAlbum.text.toString(),
                "releaseDate" to binding.ReleaseDate.text.toString(),
                "description" to binding.Description.text.toString(),
                "genre" to binding.Genre.text.toString(),
                "recordLabel" to binding.RecordLabel.text.toString()
            )

            viewModel?.createAlbum(postParams)
        }

        viewModel?.albumCreated?.observe(viewLifecycleOwner) { created ->
            if (created == true) {
                Toast.makeText(requireContext(), "Álbum creado correctamente", Toast.LENGTH_SHORT).show()

                //Limpiar back stack y navegar al fragmento principal
                findNavController().popBackStack(R.id.albumFragment, false)

                //Cambiamos estado de album created
                viewModel?.resetAlbumCreated()

            } else {
                Toast.makeText(requireContext(), "Error creando álbum", Toast.LENGTH_SHORT).show()
            }
        }

        val cancelarButton = view.findViewById<Button>(R.id.btnCancelar)
        cancelarButton.setOnClickListener {
            findNavController().navigate(R.id.action_createAlbumFragment_to_albumFragment)
        }

        val volverIcon = view.findViewById<ImageView>(R.id.backIcon)
        volverIcon.setOnClickListener {
            findNavController().navigate(R.id.action_createAlbumFragment_to_albumFragment)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}