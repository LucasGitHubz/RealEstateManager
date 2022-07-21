package com.example.realestatemanager.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.realestatemanager.database.dao.PropertyDao
import com.example.realestatemanager.model.Property

@Database(entities = [(Property::class)],version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class RealEstateManagerDatabase : RoomDatabase()  {
    abstract fun propertyDao() : PropertyDao

    companion object {
        private var INSTANCE: RealEstateManagerDatabase? = null

        fun getInstance(context: Context) : RealEstateManagerDatabase{
            if (INSTANCE == null){
                synchronized(this){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,RealEstateManagerDatabase::class.java,"RealEstateManager.db")
                        .build()
                }
            }
            return INSTANCE as RealEstateManagerDatabase
        }

        fun destroyInstance(){
            INSTANCE = null
        }
    }
}