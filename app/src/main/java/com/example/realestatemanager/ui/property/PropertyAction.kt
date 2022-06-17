package com.example.realestatemanager.ui.property

import com.example.realestatemanager.model.Property
import com.example.realestatemanager.redux.Action

sealed class PropertyAction : Action {
    data class PropertyFetched(val newProperty: Property) : PropertyAction()
    object FetchPropertyButtonTapped : PropertyAction()
    object FetchingStarted : PropertyAction()
    object FetchingCompleted : PropertyAction()
    data class FetchingFailed(val error: Throwable?) : PropertyAction()
}