package com.moviles.vinilos.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.moviles.vinilos.OnAlbumClickListener
import com.moviles.vinilos.databinding.AlbumFragmentBinding
import com.moviles.vinilos.ui.adapters.AlbumsAdapter
import com.moviles.vinilos.viewmodels.AlbumViewModel
import com.moviles.vinilos.R

class AlbumFragment : Fragment(), OnAlbumClickListener {
    private var _binding: AlbumFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: AlbumViewModel
    private var viewModelAdapter: AlbumsAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = AlbumFragmentBinding.inflate(inflater, container, false)
        viewModelAdapter = AlbumsAdapter(this)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //RecyclerView
        binding.albumsRv.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = viewModelAdapter
        }

        //Listener para búsqueda
        binding.searchInput.addTextChangedListener { editable ->
            val query = editable?.toString().orEmpty()
            viewModel.searchAlbums(query)
        }

        binding.menuIcon.setOnClickListener{
            findNavController().navigate(R.id.action_albumFragment_to_homeFragment)
        }

        binding.addIcon.setOnClickListener{
            findNavController().navigate(R.id.action_albumFragment_to_albumCreateFragment)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        activity.actionBar?.title = getString(R.string.title_albums)
        viewModel = ViewModelProvider(this, AlbumViewModel.Factory(activity.application))[AlbumViewModel::class.java]

        //Observamos cambios en la lista filtrada
        viewModel.albums.observe(viewLifecycleOwner) { list ->
            viewModelAdapter?.albums = list
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

    override fun onAlbumClick(albumId: Int) {
        // Navigate to the album detail screen
        val action = AlbumFragmentDirections.actionAlbumFragmentToAlbumDetailFragment(albumId)
        findNavController().navigate(action)
    }
}