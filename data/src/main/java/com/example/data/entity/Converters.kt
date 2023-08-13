package com.example.data.entity

import androidx.room.TypeConverter
import com.google.gson.Gson

/**
 * @author asadurrahman.qayyim
 * @date 13-Aug-2023
 */

class Converters {

    @TypeConverter
    fun listToJsonString(value: List<String>?): String = Gson().toJson(value)

    @TypeConverter
    fun jsonStringToList(value: String) = Gson().fromJson(value, Array<String>::class.java).toList()
}