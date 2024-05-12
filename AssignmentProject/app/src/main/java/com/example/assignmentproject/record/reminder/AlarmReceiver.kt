package com.example.assignmentproject.record.reminder

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.example.assignmentproject.R
import kotlin.random.Random

private const val TAG = "AlarmReceiver"

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val notificationManager =
            ContextCompat.getSystemService(context!!, NotificationManager::class.java)
        val note = intent?.getStringExtra("note")
        Log.i(TAG, "onReceive: $intent")
        Log.i(TAG, "onReceive: $note")

        // 创建通知渠道
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "channel_id",
                "Channel Name",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager?.createNotificationChannel(channel)
        }

        // 创建通知
        val notification = NotificationCompat.Builder(context, "channel_id")
            .setContentTitle("Reminder")
            .setContentText(note)
            .setSmallIcon(R.drawable.baseline_sms_24)
            .build()

        notificationManager?.notify(Random.nextInt(), notification)

    }
}