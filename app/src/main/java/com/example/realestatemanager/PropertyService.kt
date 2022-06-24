package com.example.realestatemanager

import android.util.Log
import com.example.realestatemanager.model.Property
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class PropertyService : PropertyRepository {
    private val db = Firebase.firestore

    override suspend fun fetchProperty(): List<Property> {
        val propertiesList: ArrayList<Property> = ArrayList()

        db.collection("Properties")
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    for (document in task.result) {
                        propertiesList.add(document.toObject(Property::class.java))
                    }
                } else {
                    println("error when fetching property: ${task.exception.toString()}")
                }
            }

        return propertiesList
    }

}