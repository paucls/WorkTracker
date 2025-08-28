package com.six.worktracker.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "logged_work_details")
data class LoggedWork(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val date: String,
    val timeIn: String,
    val timeOut: String,
    val totalHours: Double, // Store as Double for easier calculations
    val extraHours: Double,
    val nightOut: Boolean
)