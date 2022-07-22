package com.example.realestatemanager.service

import android.content.Context
import com.example.realestatemanager.model.Property

interface PropertyRepository {
    suspend fun fetchProperties() : List<Property>
    suspend fun sendProperties(properties: List<Property>) : Boolean
    suspend fun updateProperty(property: Property) : Boolean
    suspend fun deleteProperty(property: Property) : Boolean
}