package com.example.realestatemanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.realestatemanager.databinding.ActivityMainBinding
import com.example.realestatemanager.ui.PropertyViewModel
import com.example.realestatemanager.ui.PropertyViewState
import kotlinx.coroutines.flow.collect

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: PropertyViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[PropertyViewModel::class.java]

        lifecycleScope.launchWhenResumed {
            viewModel.viewState.collect { viewState ->
                processViewState(viewState)
            }
        }

        binding.loadPropertyBtn.setOnClickListener {
            viewModel.propertyFetchButtonTapped()
        }
    }

    private fun processViewState(viewState: PropertyViewState) {
        binding.propertyLl.visibility = if (viewState.showProgressBar) View.GONE else View.VISIBLE
        binding.propertyProgressBar.visibility = if (viewState.showProgressBar) View.VISIBLE else View.GONE
    }
}