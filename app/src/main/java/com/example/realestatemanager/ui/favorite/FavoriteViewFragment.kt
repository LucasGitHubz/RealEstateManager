package com.example.realestatemanager.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.realestatemanager.databinding.FragmentFavoriteViewBinding
import com.example.realestatemanager.ui.property.PropertyViewModel
import com.example.realestatemanager.ui.property.PropertyViewState
import kotlinx.coroutines.flow.collect

class FavoriteViewFragment: Fragment() {
    private var fragmentFavoriteViewBinding: FragmentFavoriteViewBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val binding = FragmentFavoriteViewBinding.inflate(inflater, container, false)
        fragmentFavoriteViewBinding = binding
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentFavoriteViewBinding = null
        super.onDestroyView()
    }
}