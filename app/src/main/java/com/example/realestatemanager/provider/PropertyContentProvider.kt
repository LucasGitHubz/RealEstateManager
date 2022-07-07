package com.example.realestatemanager.provider

import android.content.ContentProvider
import android.content.ContentUris
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import androidx.core.content.contentValuesOf
import com.example.realestatemanager.database.RealEstateManagerDatabase
import com.example.realestatemanager.model.Property
import java.lang.IllegalArgumentException

class PropertyContentProvider: ContentProvider() {
    companion object {
        val AUTHORITY = "com.example.realestatemanager.provider"
        val TABLE_NAME = Property::class.simpleName
        val URI_PROPERTY = Uri.parse("content://$AUTHORITY/$TABLE_NAME")
    }
    override fun onCreate(): Boolean {
        return true
    }

    override fun query(p0: Uri, p1: Array<out String>?, p2: String?, p3: Array<out String>?, p4: String?): Cursor {
        context?.let {
            return RealEstateManagerDatabase.getInstance(it).propertyDao().getPropertiesWithCursor()
        }
        throw  IllegalArgumentException("Failed to query row for uri $p0")
    }

    override fun getType(p0: Uri): String {
        return "vnd.android.cursor.property/$AUTHORITY.$TABLE_NAME"
    }

    override fun insert(p0: Uri, p1: ContentValues?): Uri? {
        context?.let {
            p1?.let { contentValues ->
                val id: Long = RealEstateManagerDatabase.getInstance(it).propertyDao().insertProperty(Property.fromContentValues(contentValues))
                it.contentResolver.notifyChange(p0, null)
                return ContentUris.withAppendedId(p0, id)
            }
        }
        throw IllegalArgumentException("Failed to insert row into $p0");
    }

    override fun delete(p0: Uri, p1: String?, p2: Array<out String>?): Int {
        context?.let {
            val count = RealEstateManagerDatabase.getInstance(it).propertyDao().deleteProperty(ContentUris.parseId(p0))
            it.contentResolver.notifyChange(p0, null)
            return count
        }
        throw IllegalArgumentException("Failed to delete row into $p0")
    }

    override fun update(p0: Uri, p1: ContentValues?, p2: String?, p3: Array<out String>?): Int {
        context?.let {
            p1?.let { contentValues ->
                val count = RealEstateManagerDatabase.getInstance(it).propertyDao().updateProperty(Property.fromContentValues(contentValues))
                it.contentResolver.notifyChange(p0, null)
                return count
            }
        }
        throw IllegalArgumentException("Failed to update row into $p0")
    }
}