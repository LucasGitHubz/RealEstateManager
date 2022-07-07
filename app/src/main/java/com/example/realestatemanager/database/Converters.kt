package com.example.realestatemanager.database

import androidx.room.TypeConverter
import com.google.common.reflect.TypeToken
import java.lang.reflect.Type


class Converters {
    @TypeConverter
    fun fromString(value: String?): ArrayList<String?>? {
        val listType: Type = object : TypeToken<ArrayList<String?>?>() {}.type
        return value?.split(",")?.let { ArrayList(it) }
    }

    @TypeConverter
    fun fromArrayList(list: List<String>): String {
        var value = ""
        for (element in list) value += "$element,"
        return value
    }
}