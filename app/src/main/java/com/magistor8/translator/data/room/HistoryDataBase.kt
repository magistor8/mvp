package com.magistor8.translator.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.magistor8.translator.data.room.HistoryDao
import com.magistor8.translator.data.room.HistoryEntity

@Database(entities = [HistoryEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converter::class)
abstract class HistoryDataBase: RoomDatabase() {
    abstract fun historyDao(): HistoryDao
}