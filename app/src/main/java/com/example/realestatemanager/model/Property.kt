package com.example.realestatemanager.model

import android.content.ContentValues
import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Property() : Parcelable {
    @PrimaryKey
    var id: String = ""
    var address: String? = ""
    var description: String? = ""
    var entryDate: String? = ""
    var saleDate: String? = ""
    var estateAgent: String? = ""
    var images: List<String>? = ArrayList()
    var price: Double = 0.0
    var beds: Int = 0
    var rooms: Int = 0
    var bathrooms: Int = 0
    var status: Boolean = false
    var surface: Double = 0.0
    var type: String? = ""
    var dateOfUpdate: String? = ""

    constructor(parcel: Parcel) : this() {
        id = parcel.readString().toString()
        address = parcel.readString()
        description = parcel.readString()
        entryDate = parcel.readString()
        saleDate = parcel.readString()
        estateAgent = parcel.readString()
        images = parcel.createStringArrayList()
        price = parcel.readDouble()
        beds = parcel.readInt()
        rooms = parcel.readInt()
        bathrooms = parcel.readInt()
        status = parcel.readByte() != 0.toByte()
        surface = parcel.readDouble()
        type = parcel.readString()
        dateOfUpdate = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(address)
        parcel.writeString(description)
        parcel.writeString(entryDate)
        parcel.writeString(saleDate)
        parcel.writeString(estateAgent)
        parcel.writeStringList(images)
        parcel.writeDouble(price)
        parcel.writeInt(beds)
        parcel.writeInt(rooms)
        parcel.writeInt(bathrooms)
        parcel.writeByte(if (status) 1 else 0)
        parcel.writeDouble(surface)
        parcel.writeString(type)
        parcel.writeString(dateOfUpdate)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Property> {
        override fun createFromParcel(parcel: Parcel): Property {
            return Property(parcel)
        }

        override fun newArray(size: Int): Array<Property?> {
            return arrayOfNulls(size)
        }

        fun fromContentValues(values: ContentValues): Property {
            var property: Property = Property()

            if (values.containsKey("id")) property.id = values.getAsString("id")
            if (values.containsKey("address")) property.address = values.getAsString("address")
            if (values.containsKey("description")) property.description = values.getAsString("description")
            if (values.containsKey("entryDate")) property.entryDate = values.getAsString("entryDate")
            if (values.containsKey("saleDate")) property.saleDate = values.getAsString("saleDate")
            if (values.containsKey("estateAgent")) property.estateAgent = values.getAsString("estateAgent")
            if (values.containsKey("images")) property.images = values.get("images") as List<String>?
            if (values.containsKey("price")) property.price = values.getAsDouble("price")
            if (values.containsKey("beds")) property.beds = values.getAsInteger("beds")
            if (values.containsKey("rooms")) property.rooms = values.getAsInteger("rooms")
            if (values.containsKey("bathrooms")) property.bathrooms = values.getAsInteger("bathrooms")
            if (values.containsKey("status")) property.status = values.getAsBoolean("status")
            if (values.containsKey("surface")) property.surface = values.getAsDouble("surface")
            if (values.containsKey("type")) property.type = values.getAsString("type")
            if (values.containsKey("dateOfUpdate")) property.dateOfUpdate = values.getAsString("dateOfUpdate")

            return property
        }
    }
}