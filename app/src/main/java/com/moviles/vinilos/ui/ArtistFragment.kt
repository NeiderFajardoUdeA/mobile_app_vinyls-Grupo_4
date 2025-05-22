package com.moviles.vinilos.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.moviles.vinilos.OnArtistClickListener
import com.moviles.vinilos.databinding.ArtistFragmentBinding
import com.moviles.vinilos.ui.adapters.ArtistsAdapter
import com.moviles.vinilos.viewmodels.ArtistViewModel
import com.moviles.vinilos.R

class ArtistFragment : Fragment(), OnArtistClickListener {
    private var _binding: ArtistFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: ArtistViewModel
    private var viewModelAdapter: ArtistsAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ArtistFragmentBinding.inflate(inflater, container, false)
        viewModelAdapter = ArtistsAdapter(this)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //RecyclerView
        binding.artistsRv.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = viewModelAdapter
        }

        //Listener para búsqueda
        binding.searchInput.addTextChangedListener { editable ->
            val query = editable?.toString().orEmpty()
            viewModel.searchArtists(query)
        }

        binding.menuIcon.setOnClickListener{
            findNavController().navigate(R.id.action_artistFragment_to_homeFragment)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.title_artists)

        viewModel = ViewModelProvider(this, ArtistViewModel.Factory(activity.application
        ))[ArtistViewModel::class.java]

        //Observamos cambios en la lista filtrada
        viewModel.artists.observe(viewLifecycleOwner) { list ->
            viewModelAdapter?.artists = list
        }

        viewModel.eventNetworkError.observe(viewLifecycleOwner) { isError ->
            if (isError) onNetworkError()
        }
    }

    private fun onNetworkError() {
        if (viewModel.isNetworkErrorShown.value == false) {
            Toast.makeText(activity, "Network Error", Toast.LENGTH_LONG).show()
            viewModel.onNetworkErrorShown()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onArtistClick(artistId: Int) {
        // Navigate to the artists detail screen
        val action = ArtistFragmentDirections.actionArtistFragmentToArtistDetailFragment(artistId)
        findNavController().navigate(action)
    }
}