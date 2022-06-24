package com.example.realestatemanager.ui.property

import android.util.Log
import com.example.realestatemanager.redux.Reducer

class PropertyReducer : Reducer<PropertyViewState, PropertyAction> {
    override fun reduce(currentState: PropertyViewState, action: PropertyAction): PropertyViewState {
        Log.v("PropertyReducer", "Processing action: $action")

        return when (action) {
            PropertyAction.FetchingStarted -> {
                stateAfterFetchingStarted(currentState)
            }
            is PropertyAction.FetchingCompleted -> {
                stateAfterFetchingCompleted(currentState, action)
            }
            is PropertyAction.FetchingFailed -> {
                stateAfterFetchingFailed(currentState)
            }
            else -> currentState
        }
    }

    private fun stateAfterFetchingStarted(currentState: PropertyViewState) =
        currentState.copy(
            showProgressBar = true
        )

    private fun stateAfterFetchingCompleted(currentState: PropertyViewState, action: PropertyAction.FetchingCompleted) =
        currentState.copy(
            properties = action.properties,
            showProgressBar = false
        )

    private fun stateAfterFetchingFailed(currentState: PropertyViewState) =
        currentState.copy(
            showProgressBar = false
        )
}