package com.example.assignmentproject.record

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.assignmentproject.ui.PreviewContainer
import com.example.assignmentproject.ui.ScreenTitleText
import com.example.assignmentproject.ui.gradientBackground
import com.example.assignmentproject.ui.rememberForResultLauncher

data class RunningRecord(val date: String, val duration: Float, val distance: Float)

private val runningRecords = listOf(
    RunningRecord("2023-04-28", 1.5f, 10.0f),
    RunningRecord("2023-04-29", 0.5f, 3.0f),
    RunningRecord("2023-04-30", 2.5f, 15.0f),
    RunningRecord("2023-05-01", 3.5f, 20.0f),
    RunningRecord("2023-05-02", 4.5f, 25.0f),
    RunningRecord("2023-05-03", 5.5f, 30.0f),
    RunningRecord("2023-05-04", 6.5f, 35.0f),
)


@Preview
@Composable
private fun PreviewReminderScreen() {
    PreviewContainer {
        RunningScreen(modifier = Modifier.gradientBackground())
    }
}

@Preview
@Composable
private fun PreviewReminderDetailsScreen() {
    PreviewContainer {
        RunningDetailsScreen(RunningRecord("2023-05-03", 5.5f, 30.0f))
    }
}

@Composable
fun RunningScreen(modifier: Modifier = Modifier) {

    CommonListSubTitleScreen(
        title = "Running",
        modifier = modifier,
        records = runningRecords,
        itemLayout = {
            val context = LocalContext.current
            val launcher = rememberForResultLauncher()
            Box(modifier = Modifier.clickable {
                launcher.launch(RecordDetailsActivity.action(context, IRecord.Running, it))
            }) {
                RunningRecordItem(it)
            }
        }
    )

}

@Composable
private fun RunningRecordItem(record: RunningRecord) {
    Row(
        modifier = Modifier
            .padding(vertical = 10.dp)
            .background(Color.Gray, RoundedCornerShape(10.dp))
            .padding(20.dp, 30.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
        ) {
            CommonItemText(
                title = "Date : ",
                value = record.date,
                fontWeight = FontWeight.SemiBold
            )
            CommonItemText(title = "Duration : ", value = "${record.duration} hours")

            CommonItemText(title = "Distance : ", value = "${record.distance} km")

        }
        Icon(
            imageVector = Icons.AutoMirrored.Default.KeyboardArrowRight,
            contentDescription = "",
            tint = Color.White
        )
    }
}

// 跑步详情
@Composable
fun RunningDetailsScreen(record: RunningRecord) {

    val recordDate = remember { mutableStateOf(record.date) }
    val dateOptions = DateOptions

    val recordDistance = remember { mutableStateOf(record.distance) }
    val recordDistanceUnit = "km"
    val distanceOptions = DistanceOptions

    val recordDuration = remember { mutableStateOf(record.duration) }
    val durationOptions = DurationOptions
    val durationUnit = "hours"

    Column {
        ScreenTitleText(title = "Running Details")

        DetailsItem(
            title = "Date:",
            content = recordDate.value,
            unit = "",
            options = dateOptions,
            onChange = { recordDate.value = dateOptions[it] }
        )

        DetailsItem(
            title = "Distance:",
            content = "${recordDistance.value.toInt()} $recordDistanceUnit",
            unit = "",
            options = distanceOptions.map { "${it.toInt()} $recordDistanceUnit" },
            onChange = {
                recordDistance.value = distanceOptions[it]
            }
        )

        DetailsItem(
            title = "Duration:",
            content = "${recordDuration.value.toInt()} $durationUnit",
            unit = "",
            options = durationOptions.map { "${it.toInt()} $durationUnit" },
            onChange = {
                recordDuration.value = durationOptions[it]
            }
        )


    }

}


