package com.example.gosocio.entities

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object Converter {
    @TypeConverter
    fun fromMyCustomObjectList(myCustomObjects: List<Items?>?): String {
        val gson = Gson()
        return gson.toJson(myCustomObjects)
    }

    @TypeConverter
    fun toMyCustomObjectList(myCustomObjectsString: String?): List<Items> {
        val gson = Gson()
        val type = object : TypeToken<List<Items?>?>() {}.type
        return gson.fromJson<List<Items>>(myCustomObjectsString, type)
    }
}

