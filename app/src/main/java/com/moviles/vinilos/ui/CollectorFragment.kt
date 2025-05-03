package com.moviles.vinilos.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.moviles.vinilos.OnCollectorClickListener
import com.moviles.vinilos.R
import com.moviles.vinilos.databinding.CollectorFragmentBinding
import com.moviles.vinilos.ui.adapters.CollectorsAdapter
import com.moviles.vinilos.viewmodels.CollectorViewModel

class CollectorFragment() : Fragment(), OnCollectorClickListener {
    private var _binding: CollectorFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: CollectorViewModel
    private var viewModelAdapter: CollectorsAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = CollectorFragmentBinding.inflate(inflater, container, false)
        viewModelAdapter = CollectorsAdapter(this)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.collectorRv.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = viewModelAdapter
        }

        binding.searchInput.addTextChangedListener { editable ->
            val query = editable?.toString().orEmpty()
            viewModel.searchCollectors(query)
        }

        binding.menuIcon.setOnClickListener{
            findNavController().navigate(R.id.action_collectorFragment_to_homeFragment)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        activity.actionBar?.title = getString(R.string.title_collectors)
        viewModel = ViewModelProvider(activity, CollectorViewModel.Factory(activity.application))
            .get(CollectorViewModel::class.java)

        viewModel.collectors.observe(viewLifecycleOwner, Observer { collectorList ->
            viewModelAdapter?.collectors = collectorList
        })

        viewModel.eventNetworkError.observe(viewLifecycleOwner) { isNetworkError ->
            if (isNetworkError) onNetworkError()
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

    override fun onCollectorClick(collectorId: Int) {
        val action = CollectorFragmentDirections.actionCollectorFragmentToCollectorDetailFragment(collectorId)
        findNavController().navigate(action)
    }

}
