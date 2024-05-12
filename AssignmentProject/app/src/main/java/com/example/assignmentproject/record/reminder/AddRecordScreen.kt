package com.example.assignmentproject.record.reminder

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.assignmentproject.database.Reminder
import com.example.assignmentproject.database.ReminderRepository
import com.example.assignmentproject.ui.ScreenTitleText
import kotlinx.coroutines.launch
import java.util.Calendar

private const val TAG = "AddRecordScreen"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddRecordScreen(repository: ReminderRepository, onSave: () -> Unit) {

    Column(
        modifier = Modifier.verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ScreenTitleText(title = "Add Reminder")
        val datePickerState = rememberDatePickerState(
            initialSelectedDateMillis = System.currentTimeMillis(),
            initialDisplayedMonthMillis = System.currentTimeMillis(),
            initialDisplayMode = DisplayMode.Picker
        )
        val timePickerState = rememberTimePickerState(is24Hour = true)
        DatePicker(state = datePickerState, showModeToggle = false, title = {})
        TimePicker(state = timePickerState)
        // note input
        val note = remember { mutableStateOf("") }
        OutlinedTextField(value = note.value, label = {
            Text(text = "Note")
        }, onValueChange = {
            note.value = it
        }, modifier = Modifier.fillMaxWidth())
        // save button
        Spacer(modifier = Modifier.size(20.dp))
        val coroutineScope = rememberCoroutineScope()
        Button(modifier = Modifier.fillMaxWidth(0.9f), onClick = {
            coroutineScope.launch {
                val calendar = Calendar.getInstance()
                Log.i(TAG, "AddRecordScreen: ${calendar.time}")
                datePickerState.selectedDateMillis?.let {
                    calendar.timeInMillis = it
                }?.let {
                    timePickerState.hour.let {
                        calendar.set(Calendar.HOUR_OF_DAY, it)
                    }
                    timePickerState.minute.let {
                        calendar.set(Calendar.MINUTE, it)
                    }
                }
                Log.i(TAG, "AddRecordScreen: ${calendar.time}")
                Log.i(
                    TAG,
                    "AddRecordScreen: ${calendar.timeInMillis}  ${System.currentTimeMillis()}"
                )

                repository.insertReminder(
                    Reminder(timestamp = calendar.timeInMillis, note = note.value)
                )
                onSave()
            }
        }) {
            Text(text = "Save")
        }
        Spacer(modifier = Modifier.size(20.dp))
    }

}

