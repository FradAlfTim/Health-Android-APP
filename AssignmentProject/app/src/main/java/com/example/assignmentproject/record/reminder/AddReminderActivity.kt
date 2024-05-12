package com.example.assignmentproject.record.reminder

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.assignmentproject.database.AppDatabase
import com.example.assignmentproject.database.ReminderRepository
import com.example.assignmentproject.ui.gradientBackground
import com.example.assignmentproject.ui.theme.AssignmentProjectTheme


class AddReminderActivity : ComponentActivity() {

    companion object {
        fun action(context: Context): Intent {
            return Intent(context, AddReminderActivity::class.java)
        }
    }

    private val repository by lazy {
        ReminderRepository(AppDatabase.getInstance(this).reminderDao())
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
                        AddRecordScreen(repository) {
                            finish()
                            SetupReminderWorker.action(this@AddReminderActivity)
                        }
                    }
                }
            }
        }
    }

}
