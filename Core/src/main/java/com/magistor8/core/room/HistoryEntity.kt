package com.magistor8.core.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.magistor8.core.domain.entities.DataModel

@Entity
data class HistoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val search: String,
    val data : List<DataModel>
)
