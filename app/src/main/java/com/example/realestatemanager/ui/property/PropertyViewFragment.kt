package com.example.realestatemanager.ui.property

import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.loader.content.AsyncTaskLoader
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.realestatemanager.R
import com.example.realestatemanager.Utils
import com.example.realestatemanager.database.RealEstateManagerDatabase
import com.example.realestatemanager.databinding.FragmentPropertyViewBinding
import com.example.realestatemanager.model.Property
import com.google.firebase.firestore.auth.User
import kotlinx.coroutines.flow.collect
import java.text.DateFormat
import java.text.ParseException
import java.util.*
import kotlin.collections.ArrayList


class PropertyViewFragment : Fragment() {
    private lateinit var viewModel: PropertyViewModel
    private lateinit var database: RealEstateManagerDatabase

    private var fragmentPropertyViewBinding: FragmentPropertyViewBinding? = null
    private var properties: List<Property> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this)[PropertyViewModel::class.java]
        context?.let { context ->
            database = RealEstateManagerDatabase.getInstance(context)
        }


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

        context?.let {
            if (Utils().isInternetAvailable(it)) {
                viewModel.sendAction(PropertyAction.FetchProperties)
            } else {
                this.viewModel.getPropertiesFromRoomDatabase(it).observe(viewLifecycleOwner, this::setProperties)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentPropertyViewBinding = null
        super.onDestroyView()
    }

    private fun setProperties(propertiesList: List<Property>) {
        val newPropertiesList = ArrayList<Property>()
        newPropertiesList.addAll(propertiesList)
        for (index in propertiesList.indices) {
            if ((properties.size - 1 >= index) && properties[index].id == propertiesList[index].id) {
                var dateFromFirestore = Date()
                var dateFromRoom = Date()
                try {
                    dateFromFirestore = Utils().fullDateFormat.parse(properties[index].dateOfUpdate ?: "") ?: Date()
                    dateFromRoom = Utils().fullDateFormat.parse(propertiesList[index].dateOfUpdate ?: "") ?: Date()
                } catch (e: ParseException) {
                    println(e.localizedMessage)
                }

                if (dateFromRoom < dateFromFirestore) {
                    newPropertiesList[index] = properties[index]
                    Thread { this.database.propertyDao().updateProperty(newPropertiesList[index]) }.start()
                }
            }
        }

        viewModel.sendAction(PropertyAction.SendProperties(newPropertiesList))
        context?.let { fragmentPropertyViewBinding?.numberOfPropertyTv?.text = it.getString(R.string.propertiesFound, newPropertiesList.size) }
        initPropertyRecyclerView(newPropertiesList)
    }

    private fun processViewState(viewState: PropertyViewState) {
        fragmentPropertyViewBinding?.progressBar?.visibility = if (viewState.showProgressBar) View.VISIBLE else View.GONE
        if (viewState.properties.isNotEmpty() && viewState.properties != properties) {
            properties = viewState.properties
            context?.let { this.viewModel.getPropertiesFromRoomDatabase(it).observe(this, this::setProperties) }

        }
    }

    private fun initPropertyRecyclerView(propertiesList: List<Property>) {
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        fragmentPropertyViewBinding?.propertyRv?.layoutManager = layoutManager
        val adapter = context?.let { PropertyRecyclerViewAdapter(propertiesList, it) }
        fragmentPropertyViewBinding?.propertyRv?.adapter = adapter
    }
}