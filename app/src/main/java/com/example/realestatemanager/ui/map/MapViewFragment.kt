package com.example.realestatemanager.ui.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.realestatemanager.databinding.FragmentMapViewBinding

class MapViewFragment: Fragment() {
    private var fragmentMapViewBinding: FragmentMapViewBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val binding = FragmentMapViewBinding.inflate(inflater, container, false)
        fragmentMapViewBinding = binding
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentMapViewBinding = null
        super.onDestroyView()
    }
}