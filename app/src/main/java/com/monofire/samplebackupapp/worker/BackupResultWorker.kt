package com.monofire.samplebackupapp.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.monofire.samplebackupapp.util.getDate
import com.monofire.samplebackupapp.util.sendNotification
import com.monofire.samplebackupapp.util.sleep

// Code with ❤
//┌─────────────────────────────┐
//│ Created by Ömer ATEŞ        │
//│ ─────────────────────────── │
//│ omerates760@gmail.com       │
//│ ─────────────────────────── │
//│ 28.06.2021 - 22:42          │
//└─────────────────────────────┘
class BackupResultWorker(
    private val context: Context,
    private val workerParameters: WorkerParameters
) :
    CoroutineWorker(context, workerParameters) {

    override suspend fun doWork(): Result {
        sleep()
        sendNotification(
            context,
            "Backup Successful",
            getDate(System.currentTimeMillis()).toString()
        )
        return Result.success()

    }


}
