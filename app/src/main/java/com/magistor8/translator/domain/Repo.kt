package com.magistor8.translator.domain

import com.magistor8.translator.data.room.HistoryEntity
import com.magistor8.translator.domain.entities.DataModel

interface Repo {
    suspend fun getData(word: String) : List<DataModel>
    suspend fun saveDataToDb(search: String, data: List<DataModel>)
    suspend fun getAllData() : List<HistoryEntity>
}