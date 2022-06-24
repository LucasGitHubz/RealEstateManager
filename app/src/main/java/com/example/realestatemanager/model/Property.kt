package com.example.realestatemanager.model

import android.os.Parcel
import android.os.Parcelable

class Property() : Parcelable {
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

    constructor(parcel: Parcel) : this() {
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
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
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
    }

}