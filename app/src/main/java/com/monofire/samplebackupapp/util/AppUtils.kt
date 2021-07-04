package com.monofire.samplebackupapp.util

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import com.monofire.samplebackupapp.R
import java.text.SimpleDateFormat
import java.util.*

// Code with ❤
//┌─────────────────────────────┐
//│ Created by Ömer ATEŞ        │
//│ ─────────────────────────── │
//│ omerates760@gmail.com       │
//│ ─────────────────────────── │
//│ 28.06.2021 - 22:22          │
//└─────────────────────────────┘
@SuppressLint("SimpleDateFormat")
fun getDate(milliSeconds: Long): String? {
    val formatter = SimpleDateFormat("dd/MM/yyyy hh:mm:ss.SSS")

    val calendar: Calendar = Calendar.getInstance()
    calendar.timeInMillis = milliSeconds
    return formatter.format(calendar.time)
}

fun sleep() {
    try {
        Thread.sleep(DELAY_TIME_MILLIS, 0)
    } catch (e: InterruptedException) {
        throw InterruptedException(e.message)
    }

}
fun sendNotification(context: Context,title: String, message: String) {
    val notificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val channel =
            NotificationChannel("default", "Default", NotificationManager.IMPORTANCE_DEFAULT)
        notificationManager.createNotificationChannel(channel)
    }
    val notification: NotificationCompat.Builder =
        NotificationCompat.Builder(context, "default")
            .setContentTitle(title)
            .setContentText(message)
            .setSmallIcon(R.mipmap.ic_launcher)
    notificationManager.notify(1, notification.build())
}