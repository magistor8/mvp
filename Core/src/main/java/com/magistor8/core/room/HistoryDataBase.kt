package com.magistor8.core.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [HistoryEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converter::class)
abstract class HistoryDataBase: RoomDatabase() {
    abstract fun historyDao(): HistoryDao
}