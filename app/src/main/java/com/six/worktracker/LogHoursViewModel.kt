package com.six.worktracker

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.six.worktracker.data.LoggedWork
import com.six.worktracker.data.LoggedWorkDao
import com.six.worktracker.data.WorkTrackerDatabase
import kotlinx.coroutines.launch

class LogHoursViewModel(application: Application) : AndroidViewModel(application) {

    private val loggedWorkDao: LoggedWorkDao

    init {
        val database = WorkTrackerDatabase.getDatabase(application)
        loggedWorkDao = database.loggedWorkDao()
    }

    fun saveLoggedWork(
        date: String,
        timeIn: String,
        timeOut: String,
        totalHoursStr: String,
        extraHoursStr: String,
        nightOut: Boolean,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        // Basic Validation (you can expand this)
        if (date.isBlank() || timeIn.isBlank() || timeOut.isBlank() || totalHoursStr.isBlank()) {
            onError("Please fill in all required fields.")
            return
        }

        val totalHours = totalHoursStr.toDoubleOrNull()
        val extraHours = extraHoursStr.toDoubleOrNull() ?: 0.0 // Default to 0 if blank

        if (totalHours == null) {
            onError("Invalid format for Total Hours.")
            return
        }

        val loggedWork = LoggedWork(
            date = date,
            timeIn = timeIn,
            timeOut = timeOut,
            totalHours = totalHours,
            extraHours = extraHours,
            nightOut = nightOut
        )

        viewModelScope.launch {
            try {
                loggedWorkDao.insertLog(loggedWork)
                onSuccess()
            } catch (e: Exception) {
                onError("Error saving data: ${e.localizedMessage}")
            }
        }
    }
}