package com.magistor8.translator.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.magistor8.translator.domain.entities.DataModel
import com.magistor8.translator.domain.entities.Meanings

@Entity
data class HistoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val search: String,
    val data : List<DataModel>
)
