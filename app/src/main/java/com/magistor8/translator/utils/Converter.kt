package com.magistor8.translator.utils

import com.magistor8.translator.data.room.HistoryEntity
import com.magistor8.translator.domain.entities.DataModel

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