package com.example.cryptocurrency.mvvm.db

import android.util.Log
import androidx.room.TypeConverter
import com.example.cryptocurrency.mvvm.model.Data
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


object CryptoDataConverter {
    @TypeConverter
    @JvmStatic
   public fun ListToJson(data: List<Data?>?): String? {
        if (data == null) return null
        val type: Type = object : TypeToken<List<Data?>?>() {}.type
        val json: String = Gson().toJson(data, type)
        Log.i("JSON", "toJson: $json")
        return if (data.isEmpty()) null else json
    }

    @TypeConverter
    @JvmStatic
    fun JsonToList(json: String?): List<Data> {
        val gson = Gson()
        val type: Type = object : TypeToken<List<Data?>?>() {}.type
        return gson.fromJson(json, type)
    }
}