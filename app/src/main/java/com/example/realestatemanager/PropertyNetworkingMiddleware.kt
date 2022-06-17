package com.example.realestatemanager

import com.example.realestatemanager.redux.Action
import com.example.realestatemanager.redux.Middleware
import com.example.realestatemanager.redux.State
import com.example.realestatemanager.redux.Store
import com.example.realestatemanager.ui.PropertyAction
import com.example.realestatemanager.ui.PropertyViewState

class PropertyNetworkingMiddleware(
    private val propertyRepository: PropertyRepository,
) : Middleware<PropertyViewState, PropertyAction> {

    override suspend fun process(
        action: PropertyAction,
        currentState: PropertyViewState,
        store: Store<PropertyViewState, PropertyAction>
    ) {
        when (action) {
            is PropertyAction.FetchPropertyButtonTapped -> {
                store.dispatch(PropertyAction.FetchingStarted)

                val isSuccessful = propertyRepository.fetchProperty()

                if (isSuccessful) {
                    store.dispatch(PropertyAction.FetchingCompleted)
                } else {
                    store.dispatch(PropertyAction.FetchingFailed(null))
                }
            }
        }
    }
}