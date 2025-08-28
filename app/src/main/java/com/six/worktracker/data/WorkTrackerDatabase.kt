package com.six.worktracker.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [LoggedWork::class], version = 1, exportSchema = false)
abstract class WorkTrackerDatabase : RoomDatabase() {

    abstract fun loggedWorkDao(): LoggedWorkDao

    companion object {
        @Volatile
        private var INSTANCE: WorkTrackerDatabase? = null

        fun getDatabase(context: Context): WorkTrackerDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WorkTrackerDatabase::class.java,
                    "work_tracker_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}