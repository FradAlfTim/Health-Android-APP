package com.example.assignmentproject.record

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.assignmentproject.ui.ScreenTitleText
import com.example.assignmentproject.ui.rememberForResultLauncher

data class SleepRecord(val date: String, val duration: Float)

private val sleepRecordList = listOf(
    SleepRecord("2023-04-28", 7.5f),
    SleepRecord("2023-04-29", 7.5f),
    SleepRecord("2023-04-30", 7.5f),
    SleepRecord("2023-05-01", 7.5f),
    SleepRecord("2023-05-02", 8.0f),
)


@Preview
@Composable
private fun PreviewSleepScreen() {
    SleepScreen()
}

@Composable
fun SleepScreen(modifier: Modifier = Modifier) {

    CommonListSubTitleScreen(
        title = "Sleep",
        modifier = modifier,
        records = sleepRecordList,
        itemLayout = {
            val context = LocalContext.current
            val launcher = rememberForResultLauncher()
            Box(modifier = Modifier.clickable {
                launcher.launch(RecordDetailsActivity.action(context, IRecord.Sleep, it))
            }) {
                SleepRecordItem(it)
            }
        }
    )
}

@Composable
private fun SleepRecordItem(record: SleepRecord) {
    Column(
        modifier = Modifier
            .padding(vertical = 10.dp)
            .background(Color.Gray, RoundedCornerShape(10.dp))
            .padding(20.dp, 30.dp)
            .fillMaxWidth()
    ) {
        CommonItemText(
            title = "Date : ",
            value = record.date,
            fontWeight = FontWeight.SemiBold
        )
        CommonItemText(title = "Duration : ", value = "${record.duration} hours")

        val quality = when {
            record.duration < 4.0 -> "Poor"
            record.duration < 6.0 -> "Fair"
            record.duration < 8.0 -> "Good"
            else -> "Excellent"
        }

        CommonItemText(title = "Quality : ", value = quality)

    }
}

// 睡觉记录详情
@Composable
fun SleepDetailsScreen(record: SleepRecord) {

    val recordDate = remember { mutableStateOf(record.date) }
    val dateOptions = DateOptions

    val recordDuration = remember { mutableStateOf(record.duration) }
    val durationOptions = DurationOptions
    val durationUnit = "hours"

    Column {
        ScreenTitleText(title = "Sleep Details")

        DetailsItem(
            title = "Date:",
            content = recordDate.value,
            unit = "",
            options = dateOptions,
            onChange = { recordDate.value = dateOptions[it] }
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
