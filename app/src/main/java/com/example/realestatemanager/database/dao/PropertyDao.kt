package com.example.realestatemanager.database.dao

import android.database.Cursor
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.realestatemanager.model.Property

@Dao
interface PropertyDao {
    @Query("SELECT * FROM Property")
    fun getProperties() : LiveData<List<Property>>

    @Query("SELECT * FROM Property")
    fun getPropertiesWithCursor() : Cursor

    @Insert
    fun insertProperties(properties: List<Property>)

    @Insert
    fun insertProperty(property: Property) : Long

    @Update
    fun updateProperty(property: Property) : Int

    @Query("DELETE FROM Property WHERE id = :propertyId")
    fun deleteProperty(propertyId: Long) : Int
}