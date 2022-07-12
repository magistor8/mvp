package com.magistor8.core.domain

import com.magistor8.core.domain.entities.DataModel
import com.magistor8.core.room.HistoryEntity


interface Repo {
    suspend fun getData(word: String) : List<DataModel>
    suspend fun saveDataToDb(search: String, data: List<DataModel>)
    suspend fun getAllData() : List<HistoryEntity>
}