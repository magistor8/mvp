package com.magistor8.core.utils

import com.magistor8.core.domain.entities.DataModel
import com.magistor8.core.room.HistoryEntity


object Converter {

    fun convertDataModelToHistoryEntity(search: String, data : List<DataModel>) : HistoryEntity {
        return HistoryEntity(
            0, search, data
        )
    }

    fun convertHistoryEntityToDataModel(entity: HistoryEntity) : List<DataModel> {
        return entity.data
    }
}