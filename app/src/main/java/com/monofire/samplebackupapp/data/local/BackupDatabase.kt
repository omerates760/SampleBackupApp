package com.monofire.samplebackupapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.monofire.samplebackupapp.data.local.dao.BackupDao
import com.monofire.samplebackupapp.data.model.User
import com.monofire.samplebackupapp.data.model.UserEntity

// Code with ❤
//┌─────────────────────────────┐
//│ Created by Ömer ATEŞ        │
//│ ─────────────────────────── │
//│ omerates760@gmail.com       │
//│ ─────────────────────────── │
//│ 24.06.2021 - 12:23          │
//└─────────────────────────────┘
@Database(
    entities = [User::class,UserEntity::class],
    version = 1, exportSchema = false
)
abstract class BackupDatabase : RoomDatabase() {
    abstract val backupDao: BackupDao
}