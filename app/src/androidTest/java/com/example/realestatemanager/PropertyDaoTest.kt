package com.example.realestatemanager

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.realestatemanager.database.RealEstateManagerDatabase
import com.example.realestatemanager.database.dao.PropertyDao
import com.example.realestatemanager.model.Property
import junit.framework.Assert.assertTrue
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
open class PropertyDaoTest {
    private lateinit var dao: PropertyDao
    private lateinit var database: RealEstateManagerDatabase

    private val property1 = Property()
    private val property2 = Property()
    private val properties: List<Property> = listOf(property1, property2)

    @get:Rule
    open val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun initDb() {
        this.database = Room.inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(), RealEstateManagerDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        dao = database.propertyDao()
    }

    @Test
    @Throws(InterruptedException::class)
    fun insertProperties() {
        properties[0].id = "6547858"
        properties[1].id = "1897667"
        dao.insertProperties(properties)
        val fetchedProperties = LiveDataTestUtil().getValue(dao.getProperties())

        assertTrue((fetchedProperties?.size ?: 0) == 2)
    }

    @After
    fun closeDB() {
        this.database.close()
    }
}