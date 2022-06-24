package com.example.realestatemanager.ui.property

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.realestatemanager.PropertyMiddleware
import com.example.realestatemanager.PropertyNetworkingMiddleware
import com.example.realestatemanager.PropertyService
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

    fun fetchProperties() {
        val action = PropertyAction.FetchProperties
        viewModelScope.launch {
            store.dispatch(action)
        }
    }
}