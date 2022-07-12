package com.magistor8.core.data

import com.magistor8.core.data.retrofit.RemoteDataSource
import com.magistor8.core.domain.Repo
import com.magistor8.core.domain.entities.DataModel
import com.magistor8.core.room.HistoryDao
import com.magistor8.core.room.HistoryEntity
import com.magistor8.core.utils.Converter

class RepoImpl(
    private val dataSource: RemoteDataSource,
    private val historyDao: HistoryDao
) : Repo {
    override suspend fun getData(word: String): List<DataModel> {
        //Проверим в локальной базе
        val data = historyDao.getDataBySearch(word)
        if (data != null) {
            return Converter.convertHistoryEntityToDataModel(data)
        }
        //Если нет, запросим через апи
        return dataSource.search(word)
    }

    override suspend fun getAllData(): List<HistoryEntity> {
        return historyDao.all()
    }

    override suspend fun saveDataToDb(search: String, data: List<DataModel>) {
        //Проверим в базе данных
        val res = historyDao.checkBySearch(search)
        if (res == null) {
            val entity = Converter.convertDataModelToHistoryEntity(search, data)
            historyDao.insert(entity)
        }
    }
}