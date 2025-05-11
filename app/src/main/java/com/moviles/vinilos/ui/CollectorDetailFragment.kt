package com.moviles.vinilos.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.moviles.vinilos.databinding.CollectorDetailBinding
import android.widget.Button
import android.widget.ImageView
import com.moviles.vinilos.viewmodels.CollectorViewModel
import com.moviles.vinilos.R
import androidx.navigation.fragment.findNavController

class CollectorDetailFragment : Fragment() {
    private var _binding: CollectorDetailBinding? = null
    private val binding get() = _binding!!
    private var viewModel: CollectorViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = CollectorDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        viewModel = ViewModelProvider(activity, CollectorViewModel.Factory(activity.application))[CollectorViewModel::class.java]
        // Observa los cambios en los coleccionistas
        viewModel!!.collectors.observe(viewLifecycleOwner) { collectorList ->
            val args: CollectorDetailFragmentArgs by navArgs()
            val collectorId = args.collectorId
            val album = collectorList.find { it.collectorId == collectorId }
            album?.let {
                binding.collector = it
            }
        }
        val volverButton = view.findViewById<Button>(R.id.volverButton)
        volverButton.setOnClickListener {
            findNavController().navigate(R.id.action_collectorDetailFragment_to_collectorFragment)
        }
        val volverIcon = view.findViewById<ImageView>(R.id.backIcon)
        volverIcon.setOnClickListener {
            findNavController().navigate(R.id.action_collectorDetailFragment_to_collectorFragment)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}