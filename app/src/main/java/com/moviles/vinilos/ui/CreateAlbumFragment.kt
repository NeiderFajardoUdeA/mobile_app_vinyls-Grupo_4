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
        val cancelarButton = view.findViewById<Button>(R.id.btnCancelar)
        cancelarButton.setOnClickListener {
            findNavController().navigate(R.id.action_createAlbumFragment_to_albumFragment)
        }

        val crearButton = view.findViewById<Button>(R.id.btnSubmit)
        crearButton.setOnClickListener {
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