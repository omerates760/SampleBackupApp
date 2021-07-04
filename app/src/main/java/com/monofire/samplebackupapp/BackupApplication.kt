package com.monofire.samplebackupapp

import android.app.Application
import com.monofire.samplebackupapp.di.appModule
import com.monofire.samplebackupapp.di.databaseModule
import com.monofire.samplebackupapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.module.Module

// Code with ❤
//┌─────────────────────────────┐
//│ Created by Ömer ATEŞ        │
//│ ─────────────────────────── │
//│ omerates760@gmail.com       │
//│ ─────────────────────────── │
//│ 24.06.2021 - 12:19          │
//└─────────────────────────────┘
class BackupApplication : Application() {
    private val moduleList: List<Module> = listOf(
        appModule, databaseModule,viewModelModule
    )

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@BackupApplication)
            modules(moduleList)
        }
    }
}