package com.example.realestatemanager.service

import com.example.realestatemanager.model.Property
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlin.collections.ArrayList
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class PropertyService : PropertyRepository {
    private val db = Firebase.firestore

    override suspend fun fetchProperties() = suspendCoroutine<ArrayList<Property>> { continuation ->
        val propertiesList: ArrayList<Property> = ArrayList()
        db.collection("Properties")
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    for (document in task.result) {
                        propertiesList.add(document.toObject(Property::class.java))
                    }
                    continuation.resumeWith(Result.success(propertiesList))
                } else {
                    println("error when fetching property: ${task.exception.toString()}")
                    task.exception?.let { continuation.resumeWithException(it) }
                }
            }

    }

    override suspend fun sendProperties(properties: List<Property>) = suspendCoroutine<Boolean> { continuation ->
        properties.forEach { property ->
            db.collection("Properties").document(property.id).set(property)
        }
        continuation.resumeWith(Result.success(true))
    }

    override suspend fun updateProperty(property: Property): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun deleteProperty(property: Property): Boolean {
        TODO("Not yet implemented")
    }
}