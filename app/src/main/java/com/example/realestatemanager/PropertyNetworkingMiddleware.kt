package com.example.realestatemanager

import android.app.Application
import com.example.realestatemanager.redux.Middleware
import com.example.realestatemanager.redux.Store
import com.example.realestatemanager.ui.property.PropertyAction
import com.example.realestatemanager.ui.property.PropertyViewState

class PropertyNetworkingMiddleware(
    private val propertyRepository: PropertyRepository,
) : Middleware<PropertyViewState, PropertyAction> {

    override suspend fun process(
        action: PropertyAction,
        currentState: PropertyViewState,
        store: Store<PropertyViewState, PropertyAction>
    ) {
        when (action) {
            is PropertyAction.FetchProperties -> {
                store.dispatch(PropertyAction.FetchingStarted)

                val properties = propertyRepository.fetchProperties()

                if (properties.isNotEmpty()) {
                    store.dispatch(PropertyAction.FetchingCompleted(properties))
                } else {
                    store.dispatch(PropertyAction.FetchingFailed(null))
                }
            }
        }
    }
}