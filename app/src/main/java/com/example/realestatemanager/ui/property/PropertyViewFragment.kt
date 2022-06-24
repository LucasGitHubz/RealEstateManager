package com.example.realestatemanager.ui.property

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.realestatemanager.R
import com.example.realestatemanager.databinding.FragmentFavoriteViewBinding
import com.example.realestatemanager.databinding.FragmentPropertyViewBinding
import com.example.realestatemanager.model.Property
import kotlinx.coroutines.flow.collect
import java.util.*
import kotlin.collections.ArrayList

class PropertyViewFragment : Fragment() {
    private lateinit var viewModel: PropertyViewModel

    private var fragmentPropertyViewBinding: FragmentPropertyViewBinding? = null
    private var properties: List<Property> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this)[PropertyViewModel::class.java]

        lifecycleScope.launchWhenResumed {
            viewModel.viewState.collect { viewState ->
                processViewState(viewState)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val binding = FragmentPropertyViewBinding.inflate(inflater, container, false)
        fragmentPropertyViewBinding = binding
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.fetchProperties()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentPropertyViewBinding = null
        super.onDestroyView()
    }

    private fun processViewState(viewState: PropertyViewState) {
        fragmentPropertyViewBinding?.progressBar?.visibility = if (viewState.showProgressBar) View.VISIBLE else View.GONE
        if (viewState.properties.isNotEmpty() && viewState.properties != properties) {
            properties = viewState.properties
            initPropertyRecyclerView()
        }
    }

    private fun initPropertyRecyclerView() {
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        fragmentPropertyViewBinding?.propertyRv?.layoutManager = layoutManager
        val adapter = context?.let { PropertyRecyclerViewAdapter(properties, it) }
        fragmentPropertyViewBinding?.propertyRv?.adapter = adapter
    }
}