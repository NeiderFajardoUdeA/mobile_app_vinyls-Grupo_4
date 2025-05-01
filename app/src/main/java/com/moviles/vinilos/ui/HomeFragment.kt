package com.moviles.vinilos.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.moviles.vinilos.R

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.home_fragment, container, false)

        val albumesButton = view.findViewById<Button>(R.id.albumesButton)
        albumesButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_albumFragment)
        }

        val artistsButton = view.findViewById<Button>(R.id.artistsButton)
        artistsButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_artistFragment)
        }

        val collectorsButton = view.findViewById<Button>(R.id.collectorButton)
        collectorsButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_collectorFragment)
        }

        return view
    }
}
