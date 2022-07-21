package com.example.realestatemanager.ui.property

import com.example.realestatemanager.model.Property
import com.example.realestatemanager.redux.Action

sealed class PropertyAction : Action {
    object FetchProperties : PropertyAction()
    object FetchingStarted : PropertyAction()
    object SendingCompleted : PropertyAction()
    data class SendProperties(val properties: List<Property>) : PropertyAction()
    data class FetchingCompleted(val properties: List<Property>) : PropertyAction()
    data class FetchingFailed(val error: Throwable?) : PropertyAction()
}