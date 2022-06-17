package com.example.realestatemanager

interface PropertyRepository {
    suspend fun fetchProperty() : Boolean
}