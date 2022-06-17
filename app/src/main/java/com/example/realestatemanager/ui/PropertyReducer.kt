package com.example.realestatemanager.ui

import android.util.Log
import com.example.realestatemanager.redux.Reducer

class PropertyReducer : Reducer<PropertyViewState, PropertyAction> {
    override fun reduce(currentState: PropertyViewState, action: PropertyAction): PropertyViewState {
        Log.v("PropertyReducer", "Processing action: $action")

        return when (action) {
            is PropertyAction.PropertyFetched -> {
                stateWithNewProperty(currentState, action)
            }
            PropertyAction.FetchingStarted -> {
                stateAfterFetchingStarted(currentState)
            }
            PropertyAction.FetchingCompleted -> {
                stateAfterFetchingCompleted(currentState)
            }
            is PropertyAction.FetchingFailed -> {
                stateAfterFetchingFailed(currentState)
            }
            else -> currentState
        }
    }

    private fun stateWithNewProperty(currentState: PropertyViewState, action: PropertyAction.PropertyFetched) =
        currentState.copy(
            property = action.newProperty,
        )

    private fun stateAfterFetchingStarted(currentState: PropertyViewState) =
        currentState.copy(
            showProgressBar = true
        )

    private fun stateAfterFetchingCompleted(currentState: PropertyViewState) =
        currentState.copy(
            showProgressBar = false
        )

    private fun stateAfterFetchingFailed(currentState: PropertyViewState) =
        currentState.copy(
            showProgressBar = false
        )
}