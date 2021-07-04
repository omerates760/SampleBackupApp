package com.monofire.samplebackupapp.di

import android.content.Context
import androidx.work.WorkManager
import com.monofire.samplebackupapp.data.repository.UserRepository
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module


// Code with ❤
//┌─────────────────────────────┐
//│ Created by Ömer ATEŞ        │
//│ ─────────────────────────── │
//│ omerates760@gmail.com       │
//│ ─────────────────────────── │
//│ 24.06.2021 - 12:41          │
//└─────────────────────────────┘
val appModule = module {
    single { UserRepository(get()) }
    factory { workManagerGetInstance(androidApplication()) }
}

fun workManagerGetInstance(context: Context): WorkManager = WorkManager.getInstance(context)
