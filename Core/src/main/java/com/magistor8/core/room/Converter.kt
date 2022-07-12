package com.magistor8.core.room

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.magistor8.core.domain.entities.DataModel

@ProvidedTypeConverter
class Converter(private val gson: Gson) {

    @TypeConverter
    fun listDataToGson(list: List<DataModel>) : String {
        return gson.toJson(list)
    }

    @TypeConverter
    fun gsonToListData(gsonStr: String) : List<DataModel> {
        val itemType = object : TypeToken<List<DataModel>>() {}.type
        return gson.fromJson(gsonStr, itemType)
    }
}