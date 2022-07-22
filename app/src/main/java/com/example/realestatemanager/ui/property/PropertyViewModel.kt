package com.example.realestatemanager.ui.property

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.realestatemanager.service.PropertyMiddleware
import com.example.realestatemanager.service.PropertyNetworkingMiddleware
import com.example.realestatemanager.service.PropertyService
import com.example.realestatemanager.database.RealEstateManagerDatabase
import com.example.realestatemanager.model.Property
import com.example.realestatemanager.redux.Store
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PropertyViewModel : ViewModel() {
    private val store = Store(
        initialState = PropertyViewState(),
        reducer = PropertyReducer(),
        middlewares = listOf(
            PropertyMiddleware(),
            PropertyNetworkingMiddleware(
                propertyRepository = PropertyService(),
            )
        )
    )

    val viewState: StateFlow<PropertyViewState> = store.state

    fun sendAction(action: PropertyAction) {
        viewModelScope.launch {
            store.dispatch(action)
        }
    }

    fun getPropertiesFromRoomDatabase(context: Context): LiveData<List<Property>> {
        val database = RealEstateManagerDatabase.getInstance(context)
        return database.propertyDao().getProperties()
    }
}