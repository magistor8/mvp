package com.magistor8.core.room

import androidx.room.*

@Dao
interface HistoryDao {

    @Query("SELECT * FROM HistoryEntity")
    fun all(): List<HistoryEntity>

    @Query("SELECT * FROM HistoryEntity WHERE search LIKE :search")
    fun getDataBySearch(search: String): HistoryEntity?

    @Query("SELECT id FROM HistoryEntity WHERE search LIKE :search")
    fun checkBySearch(search: String): Long?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(entity: HistoryEntity)

    @Update
    fun update(entity: HistoryEntity)

    @Delete
    fun delete(entity: HistoryEntity)

}