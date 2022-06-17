package com.example.realestatemanager.ui.property

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.realestatemanager.R
import com.example.realestatemanager.databinding.FragmentPropertyViewBinding
import kotlinx.coroutines.flow.collect

class PropertyViewFragment : Fragment() {
    private var fragmentPropertyViewBinding: FragmentPropertyViewBinding? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val binding = FragmentPropertyViewBinding.inflate(inflater, container, false)
        fragmentPropertyViewBinding = binding
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentPropertyViewBinding = null
        super.onDestroyView()
    }
}