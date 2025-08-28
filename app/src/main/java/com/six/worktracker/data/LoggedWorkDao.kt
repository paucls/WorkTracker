package com.six.worktracker.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface LoggedWorkDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertLog(loggedWork: LoggedWork)

    @Query("SELECT * FROM logged_work_details ORDER BY date DESC")
    fun getAllLogs(): Flow<List<LoggedWork>>
}