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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.assignmentproject.record.reminder.ReminderScreen
import com.example.assignmentproject.ui.gradientBackground
import com.example.assignmentproject.ui.theme.AssignmentProjectTheme


class RecordsActivity : ComponentActivity() {

    companion object {
        private const val TYPE = "type"
        fun action(context: Context, type: IRecord): Intent {
            return Intent(context, RecordsActivity::class.java)
                .putExtra(TYPE, type.title())
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
                        }
                    }
                }
            }
        }
    }

    @Composable
    private fun BuildScreen(record: IRecord) {
        when (record) {
            IRecord.Reminder -> ReminderScreen()
            IRecord.Running -> RunningScreen()
            IRecord.Sleep -> SleepScreen()
            IRecord.Swimming -> SwimmingScreen()
            IRecord.Walking -> WalkingScreen()
        }
    }

}
