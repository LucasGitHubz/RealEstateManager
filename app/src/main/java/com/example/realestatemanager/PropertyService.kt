package com.example.realestatemanager

import android.util.Log
import com.example.realestatemanager.model.Property
import com.google.android.gms.common.api.Response
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.*
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
}