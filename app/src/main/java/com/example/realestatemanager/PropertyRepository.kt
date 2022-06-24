package com.example.realestatemanager

import com.example.realestatemanager.model.Property

interface PropertyRepository {
    suspend fun fetchProperty() : List<Property>
}