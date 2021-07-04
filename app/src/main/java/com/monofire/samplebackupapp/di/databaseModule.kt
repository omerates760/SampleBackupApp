package com.monofire.samplebackupapp.di

import android.app.Application
import androidx.room.Room
import com.monofire.samplebackupapp.data.local.BackupDatabase
import com.monofire.samplebackupapp.data.local.dao.BackupDao
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

// Code with ❤
//┌─────────────────────────────┐
//│ Created by Ömer ATEŞ        │
//│ ─────────────────────────── │
//│ omerates760@gmail.com       │
//│ ─────────────────────────── │
//│ 24.06.2021 - 12:45          │
//└─────────────────────────────┘
val databaseModule = module {

    fun provideDatabase(application: Application): BackupDatabase {
        return Room.databaseBuilder(application, BackupDatabase::class.java, "userDb")
            .fallbackToDestructiveMigration()
            .build()
    }

    fun provideCountriesDao(database: BackupDatabase): BackupDao {
        return database.backupDao
    }

    single { provideDatabase(androidApplication()) }
    single { provideCountriesDao(get()) }
}