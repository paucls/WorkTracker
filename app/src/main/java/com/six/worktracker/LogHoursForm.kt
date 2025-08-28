package com.six.worktracker

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.six.worktracker.ui.theme.WorkTrackerTheme

@Composable
fun LogHoursForm() {
    var date by remember { mutableStateOf("") }
    var timeIn by remember { mutableStateOf("") }
    var timeOut by remember { mutableStateOf("") }
    var totalHours by remember { mutableStateOf("") }
    var extraHours by remember { mutableStateOf("") }
    var nightOut by remember { mutableStateOf(false) }

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
            value = timeIn,
            onValueChange = {
                if (it.all { it.isDigit() || it == ':' }) {
                    timeIn = it
                }
            },
            label = { Text("Time In") }
        )
        OutlinedTextField(
            value = timeOut,
            onValueChange = {
                if (it.all { it.isDigit() || it == ':' }) {
                    timeOut = it
                }
            },
            label = { Text("Time Out") }
        )
        OutlinedTextField(
            value = totalHours,
            onValueChange = {
                if (it.matches(Regex("^(\\d+(\\.\\d+)?)$"))) {
                    totalHours = it
                }
            },
            label = { Text("Total Hours") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Decimal)
        )
        OutlinedTextField(
            value = extraHours,
            onValueChange = {
                if (it.matches(Regex("^(\\d+(\\.\\d+)?)$"))) {
                    extraHours = it
                }
            },
            label = { Text("Extra Hours") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Decimal)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Checkbox(
                checked = nightOut,
                onCheckedChange = { nightOut = it }
            )
            Text("Night Out (Sleep in hotel)")
        }
        Button(
            onClick = {
                // Save the data here
                saveData(date, timeIn, timeOut, totalHours, extraHours, nightOut)
            }
        ) {
            Text("Save")
        }
    }
}

fun saveData(
    date: String,
    timeIn: String,
    timeOut: String,
    totalHours: String,
    extraHours: String,
    nightOut: Boolean
) {
    // TO DO: Implement data saving logic here
    // For now, just print the data to the console
    println("Saving data: Date=$date, Time In=$timeIn, Time Out=$timeOut, Total Hours=$totalHours, Extra Hours=$extraHours, Night Out=$nightOut")
}

@Preview(showBackground = true)
@Composable
fun LogHoursFormPreview() {
    WorkTrackerTheme {
        LogHoursForm()
    }
}