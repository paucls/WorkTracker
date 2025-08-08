package com.six.worktracker

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.six.worktracker.ui.theme.WorkTrackerTheme

@Composable
fun LogHoursForm() {
    var date by remember { mutableStateOf("") }
    var employeeName by remember { mutableStateOf("") }
    var hoursWorked by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = date,
            onValueChange = { date = it },
            label = { Text("Date") }
        )
        OutlinedTextField(
            value = employeeName,
            onValueChange = { employeeName = it },
            label = { Text("Employee Name") }
        )
        OutlinedTextField(
            value = hoursWorked,
            onValueChange = { hoursWorked = it },
            label = { Text("Hours Worked") }
        )
        Button(
            onClick = {
                // Save the data here
                saveData(date, employeeName, hoursWorked)
            }
        ) {
            Text("Save")
        }
    }
}

fun saveData(date: String, employeeName: String, hoursWorked: String) {
    // TO DO: Implement data saving logic here
    // For now, just print the data to the console
    println("Saving data: Date=$date, Employee Name=$employeeName, Hours Worked=$hoursWorked")
}

@Preview(showBackground = true)
@Composable
fun LogHoursFormPreview() {
    WorkTrackerTheme {
        LogHoursForm()
    }
}