package com.example.assignmentproject.record.reminder

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.assignmentproject.database.AppDatabase
import com.example.assignmentproject.database.ReminderRepository
import com.example.assignmentproject.record.CommonItemText
import com.example.assignmentproject.record.CommonListScreen
import com.example.assignmentproject.record.IRecord
import com.example.assignmentproject.record.RecordDetailsActivity
import com.example.assignmentproject.ui.ScreenSubTitleText
import com.example.assignmentproject.ui.rememberForResultLauncher
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class ReminderRecord(
    val id: Int,
    val date: String,
    val time: String,
    val note: String
)

@Preview
@Composable
private fun PreviewReminderScreen() {
    ReminderScreen()
}

val dateFormat: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
val timeFormat: SimpleDateFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())

val listDate = listOf(
    "2023-01-01",
    "2023-01-02",
    "2023-01-03",
    "2023-01-04",
    "2023-01-05",
    "2023-01-06",
)

@Composable
fun ReminderScreen(modifier: Modifier = Modifier) {

    val context = LocalContext.current
    val repository = remember { ReminderRepository(AppDatabase.getInstance(context).reminderDao()) }
    val reminderRecords = remember { mutableStateListOf<ReminderRecord>() }
    LaunchedEffect(key1 = Unit) {
        repository.getAllReminders().collect {
            reminderRecords.clear()
            reminderRecords.addAll(it.map {
                val date = Date(it.timestamp)
                ReminderRecord(
                    id = it.id,
                    date = dateFormat.format(date),
                    time = timeFormat.format(date),
                    note = it.note
                )
            })
        }
    }

    CommonListScreen(
        modifier = modifier,
        title = "Reminder",
        records = reminderRecords,
        subTitleLayout = { ReminderSubtitle1() }) {
        val launcher = rememberForResultLauncher()
        Box(modifier = Modifier.clickable {
            launcher.launch(RecordDetailsActivity.action(context, IRecord.Reminder, it))
        }) { ReminderRecordItem(it) }
    }

}

@Composable
private fun ReminderSubtitle1() {
    val context = LocalContext.current
    val launcher = rememberForResultLauncher()
    ScreenSubTitleText(title = "Add New +", modifier = Modifier
        .padding(top = 20.dp)
        .clickable {
            launcher.launch(AddReminderActivity.action(context))
        })

}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun ReminderSubtitle2() {

    val isExpanded = remember { mutableStateOf(false) }
    val selectedDate = remember { mutableStateOf(listDate.first()) }

    ExposedDropdownMenuBox(
        expanded = isExpanded.value,
        onExpandedChange = { isExpanded.value = it },
    ) {
        ScreenSubTitleText(
            title = "Date : ${selectedDate.value}  ▼",
            modifier = Modifier
                .menuAnchor()
                .padding(top = 20.dp)
                .clickable { isExpanded.value = true },
            fontWeight = null
        )
        ExposedDropdownMenu(
            expanded = isExpanded.value,
            onDismissRequest = { isExpanded.value = false }
        )
        {
            listDate.forEach { selectionOption ->
                DropdownMenuItem(
                    text = { Text(selectionOption) },
                    onClick = {
                        selectedDate.value = selectionOption
                        isExpanded.value = false
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                )
            }
        }
    }
}

@Composable
private fun ReminderRecordItem(record: ReminderRecord) {

    Row(
        modifier = Modifier
            .padding(vertical = 10.dp)
            .background(Color.Gray, RoundedCornerShape(10.dp))
            .padding(20.dp, 30.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            CommonItemText(
                title = "Date : ",
                value = record.date,
                fontWeight = FontWeight.SemiBold
            )
            CommonItemText(title = "Time : ", value = record.time)
            CommonItemText(title = "Note : ", value = record.note)
        }
        Icon(
            imageVector = Icons.AutoMirrored.Default.KeyboardArrowRight,
            contentDescription = "",
            tint = Color.White
        )
    }

}

// 提醒记录详情
@Composable
fun ReminderDetailsScreen(record: ReminderRecord) {
    Column {
        ScreenSubTitleText(title = "Reminder Details")
        Column(
            modifier = Modifier
                .padding(20.dp)
                .background(Color.Gray, RoundedCornerShape(10.dp))
                .padding(20.dp, 30.dp)
        ) {

            CommonItemText(
                title = "Date : ",
                value = record.date,
                fontWeight = FontWeight.SemiBold
            )
            CommonItemText(title = "Time : ", value = record.time)
            CommonItemText(title = "Note : ", value = record.note)
        }
    }

}

