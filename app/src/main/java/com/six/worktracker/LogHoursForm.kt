package com.six.worktracker

import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.six.worktracker.ui.theme.WorkTrackerTheme


@Composable
fun LogHoursForm(
    logHoursViewModel: LogHoursViewModel = viewModel()
) {
    var date by remember { mutableStateOf("") }
    var timeIn by remember { mutableStateOf("") }
    var timeOut by remember { mutableStateOf("") }
    var totalHours by remember { mutableStateOf("") }
    var extraHours by remember { mutableStateOf("") }
    var nightOut by remember { mutableStateOf(false) }

    val context = LocalContext.current

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
                println("Call ViewModel to save data: Date=$date, Time In=$timeIn, Time Out=$timeOut, Total Hours=$totalHours, Extra Hours=$extraHours, Night Out=$nightOut")
                logHoursViewModel.saveLoggedWork(
                    date = date,
                    timeIn = timeIn,
                    timeOut = timeOut,
                    totalHoursStr = totalHours,
                    extraHoursStr = extraHours,
                    nightOut = nightOut,
                    onSuccess = {
                        Toast.makeText(context, "Work logged successfully!", Toast.LENGTH_SHORT).show()
                        // Clear form fields after successful save
                        date = ""
                        timeIn = ""
                        timeOut = ""
                        totalHours = ""
                        extraHours = ""
                        nightOut = false
                    },
                    onError = { errorMessage ->
                        Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
                    }
                )
            }
        ) {
            Text("Save")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LogHoursFormPreview() {
    WorkTrackerTheme {
        LogHoursForm()
    }
}