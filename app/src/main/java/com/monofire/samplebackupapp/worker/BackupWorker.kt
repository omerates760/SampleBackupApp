package com.monofire.samplebackupapp.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.monofire.samplebackupapp.R
import com.monofire.samplebackupapp.data.repository.UserRepository
import com.monofire.samplebackupapp.util.getDate
import com.monofire.samplebackupapp.util.sendNotification
import com.monofire.samplebackupapp.util.sleep
import org.koin.java.KoinJavaComponent.inject


// Code with ❤
//┌─────────────────────────────┐
//│ Created by Ömer ATEŞ        │
//│ ─────────────────────────── │
//│ omerates760@gmail.com       │
//│ ─────────────────────────── │
//│ 27.06.2021 - 14:26          │
//└─────────────────────────────┘
class BackupWorker(private val context: Context, private val workerParameters: WorkerParameters) :
    CoroutineWorker(context, workerParameters) {

    override suspend fun doWork(): Result {
        val repository: UserRepository by inject(UserRepository::class.java)

        return try {
            sleep()
            sendNotification(
                context,
                "Backup Started",
                getDate(System.currentTimeMillis()).toString()
            )

            val users = repository.fetchAllUsersWithoutLiveDataFromLocal()
            repository.insertUsersToRemote(users)
            val resultCompletedDate =
                workDataOf(context.getString(R.string.worker_completed_time) to getDate(System.currentTimeMillis()).toString())

            Result.success(resultCompletedDate)

        } catch (e: Exception) {
            Result.failure()
        }

    }


}