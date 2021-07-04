package com.monofire.samplebackupapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import androidx.work.*
import com.monofire.samplebackupapp.data.model.UserEntity
import com.monofire.samplebackupapp.data.repository.UserRepository
import com.monofire.samplebackupapp.util.DELAY_TIME_MILLIS
import com.monofire.samplebackupapp.util.USER_BACKUP_WORKER_KEY
import com.monofire.samplebackupapp.worker.BackupResultWorker
import com.monofire.samplebackupapp.worker.BackupWorker
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.TimeUnit

// Code with ❤
//┌─────────────────────────────┐
//│ Created by Ömer ATEŞ        │
//│ ─────────────────────────── │
//│ omerates760@gmail.com       │
//│ ─────────────────────────── │
//│ 24.06.2021 - 18:44          │
//└─────────────────────────────┘
class MainViewModel(private val repository: UserRepository, private val workManager: WorkManager) :
    ViewModel() {
    val userListFromLocal: LiveData<List<UserEntity>> = repository.fetchAllUsersFromLocal()
    val backupWorkerState: LiveData<List<WorkInfo>> =
        workManager.getWorkInfosForUniqueWorkLiveData(USER_BACKUP_WORKER_KEY)


    fun insertUser(user: UserEntity) {
        viewModelScope.launch {
            repository.insertUserToLocal(user)
        }
    }

    fun fetchUserListFromRemote(): LiveData<List<UserEntity>> = liveData {
        withContext(Dispatchers.IO) {
            emit(repository.fetchAllUsersFromRemote())
        }
    }

    fun deleteAllUserFromLocal() {
        viewModelScope.launch {
            repository.deleteAllUserFromLocal()
        }
    }

    fun startBackupWithOneTime() {
        val mConstraints =
            Constraints.Builder().setRequiresStorageNotLow(true).setRequiresCharging(true).build()
        val backupWorkerRequest =
            OneTimeWorkRequestBuilder<BackupWorker>().setConstraints(mConstraints).build()
        val backupResultWorkerRequest =
            OneTimeWorkRequestBuilder<BackupResultWorker>().setConstraints(mConstraints).build()

        workManager.beginUniqueWork(
            USER_BACKUP_WORKER_KEY, ExistingWorkPolicy.KEEP,
            backupWorkerRequest
        ).then(backupResultWorkerRequest).enqueue()
    }

    fun startBackupWithPeriodicTime() {
        val mConstraints =
            Constraints.Builder().setRequiresStorageNotLow(true).setRequiresCharging(true).build()
        val backupWorkerRequest =
            PeriodicWorkRequestBuilder<BackupWorker>(DELAY_TIME_MILLIS, TimeUnit.MINUTES).build()

        workManager.enqueueUniquePeriodicWork(
            USER_BACKUP_WORKER_KEY, ExistingPeriodicWorkPolicy.KEEP, backupWorkerRequest
        )
    }


    fun cancelBackup() {
        workManager.cancelUniqueWork(USER_BACKUP_WORKER_KEY)
    }


}