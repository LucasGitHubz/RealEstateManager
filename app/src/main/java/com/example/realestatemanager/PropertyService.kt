package com.example.realestatemanager

import kotlinx.coroutines.delay

class PropertyService : PropertyRepository {
    override suspend fun fetchProperty(): Boolean {
        delay(2000)
        return true
    }
}