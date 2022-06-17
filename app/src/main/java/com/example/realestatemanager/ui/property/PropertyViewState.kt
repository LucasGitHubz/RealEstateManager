package com.example.realestatemanager.ui.property

import com.example.realestatemanager.model.Property
import com.example.realestatemanager.redux.State

data class PropertyViewState(
    val property: Property = Property(),
    val showProgressBar: Boolean = false,
    val errorMessage: String? = null
) : State