package com.example.data.entity

import androidx.room.TypeConverter
import com.google.gson.Gson

/**
 * @author asadurrahman.qayyim
 * @date 13-Aug-2023
 */

class Converters {

    @TypeConverter
    fun listToJsonString(value: List<String>?): String? {
        return value?.let { Gson().toJson(value) }
    }

    @TypeConverter
    fun jsonStringToList(value: String?): List<String>? {
        return value?.let {
            Gson().fromJson(value, Array<String>::class.java).toList()
        }
    }
}