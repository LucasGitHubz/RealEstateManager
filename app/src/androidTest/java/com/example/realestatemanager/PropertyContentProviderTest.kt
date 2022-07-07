package com.example.realestatemanager

import android.content.ContentResolver
import android.content.ContentUris
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.realestatemanager.database.RealEstateManagerDatabase
import com.example.realestatemanager.provider.PropertyContentProvider
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.notNullValue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class PropertyContentProviderTest {
    // FOR DATA

    // FOR DATA

    // FOR DATA
    private var mContentResolver: ContentResolver? = null

    // DATA SET FOR TEST

    // DATA SET FOR TEST
    private val PROPERTY_ID: Long = 1

    @Before
    fun setUp() {
        Room.inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(), RealEstateManagerDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        mContentResolver = InstrumentationRegistry.getInstrumentation().context.contentResolver
    }

    @Test
    fun insertAndGetItem() {

        // BEFORE : Adding demo property
        val propertyUri: Uri? = mContentResolver!!.insert(PropertyContentProvider.URI_PROPERTY, generateItem())

        // TEST
        val cursor: Cursor? = mContentResolver!!.query(
            ContentUris.withAppendedId(PropertyContentProvider.URI_PROPERTY, PROPERTY_ID),
            null, null, null, null)
        assertThat(cursor, notNullValue())
        assertThat(cursor?.count, `is`(1))
        assertThat(cursor?.moveToFirst(), `is`(true))
        assertThat(cursor?.getString(cursor.getColumnIndexOrThrow("id")), `is`("15343LHGRE47dzJHG"))
    }

    private fun generateItem(): ContentValues? {
        val values = ContentValues()
        values.put("id", "15343LHGRE47dzJHG")
        values.put("address", "21, jump street")
        values.put("price", "14.99")
        values.put("beds", "19")
        values.put("bathrooms", "28")
        return values
    }
}