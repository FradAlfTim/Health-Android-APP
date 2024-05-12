package com.example.assignmentproject.record

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.assignmentproject.record.reminder.ReminderDetailsScreen
import com.example.assignmentproject.record.reminder.ReminderRecord
import com.example.assignmentproject.ui.gradientBackground
import com.example.assignmentproject.ui.theme.AssignmentProjectTheme
import com.example.assignmentproject.utils.JsonParse


class RecordDetailsActivity : ComponentActivity() {

    companion object {
        private const val TYPE = "type"
        private const val BEAN = "bean"
        fun action(context: Context, type: IRecord, bean: Any): Intent {
            return Intent(context, RecordDetailsActivity::class.java)
                .putExtra(TYPE, type.title())
                .putExtra(BEAN, JsonParse.toJson(bean))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AssignmentProjectTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .gradientBackground()
                            .fillMaxSize()
                            .padding(innerPadding)
                            .padding(start = 15.dp, end = 15.dp, top = 20.dp)
                    ) {
                        intent.getStringExtra(TYPE)?.let {
                            BuildScreen(it.coverToIRecord())
                        } ?: let {
                            Text(text = "数据错误")
                        }
                    }
                }
            }
        }
    }

    @Composable
    private fun BuildScreen(type: IRecord) {
        val beanJson = intent.getStringExtra(BEAN)
        when (type) {
            IRecord.Reminder -> {
                ReminderDetailsScreen(JsonParse.fromJson(beanJson, ReminderRecord::class.java))
            }

            IRecord.Running -> {
                RunningDetailsScreen(JsonParse.fromJson(beanJson, RunningRecord::class.java))
            }

            IRecord.Sleep -> {
                SleepDetailsScreen(JsonParse.fromJson(beanJson, SleepRecord::class.java))
            }

            IRecord.Swimming -> {
                SwimmingDetailsScreen(JsonParse.fromJson(beanJson, SwimmingRecord::class.java))
            }

            IRecord.Walking -> {
                WalkingDetailsScreen(JsonParse.fromJson(beanJson, WalkingRecord::class.java))
            }
        }
    }

}
