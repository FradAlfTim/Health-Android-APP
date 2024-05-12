package com.example.assignmentproject.record.reminder

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.work.Constraints
import androidx.work.CoroutineWorker
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import com.example.assignmentproject.database.AppDatabase
import com.example.assignmentproject.database.ReminderRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.concurrent.TimeUnit


class SetupReminderWorker(context: Context, workerParameters: WorkerParameters) :
    CoroutineWorker(context, workerParameters) {

    companion object {
        private const val TAG = "SetupReminderWorker"
        fun action(context: Context) {
            val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.NOT_REQUIRED)
                .build()

            val reminderWorkRequest =
                PeriodicWorkRequestBuilder<SetupReminderWorker>(15, TimeUnit.MINUTES)
                    .setConstraints(constraints)
                    .build()

            WorkManager.getInstance(context).enqueueUniquePeriodicWork(
                "reminderWork",
                ExistingPeriodicWorkPolicy.KEEP,
                reminderWorkRequest
            ).state.observeForever {
                Log.i(TAG, " enqueueUniquePeriodicWork reminderWork action: $it")
            }

        }
    }

    private val repository by lazy {
        ReminderRepository(AppDatabase.getInstance(context).reminderDao())
    }

    override suspend fun doWork(): Result {
        try {
            Log.i(TAG, "doWork: 1")
            // 查询所有的提醒记录
            withContext(Dispatchers.IO) {
                Log.i(TAG, "doWork: 2")
                repository.getAllReminders().collect {
                    Log.i(TAG, "doWork: currentTimeMillis : ${System.currentTimeMillis()}")
                    Log.i(TAG, "doWork: all reminders : $it")
                    it
                        .filter { it.timestamp > System.currentTimeMillis() }
                        .forEach { reminder ->
                            setAlarm(reminder.id, reminder.timestamp, reminder.note)
                        }
                }
            }
            return Result.success()
        } catch (e: Exception) {
            return Result.failure()
        }
    }

    private fun setAlarm(reminderId: Int, timestamp: Long, note: String) {
        Log.i(TAG, "setAlarm : $reminderId $timestamp $note")
        val alarmManager =
            applicationContext.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(applicationContext, AlarmReceiver::class.java)
        intent.putExtra("note", note)
        val pendingIntent = PendingIntent.getBroadcast(
            applicationContext,
            reminderId,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        alarmManager.setExact(AlarmManager.RTC_WAKEUP, timestamp, pendingIntent)
    }
}