package com.magistor8.translator.data.room

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.magistor8.translator.domain.entities.DataModel
import com.magistor8.translator.domain.entities.Meanings
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class Converter: KoinComponent{

    private val gson: Gson by inject()

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