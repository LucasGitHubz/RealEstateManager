package com.example.realestatemanager

import android.content.Context
import com.example.realestatemanager.model.Property

interface PropertyRepository {
    suspend fun fetchProperties() : List<Property>
}