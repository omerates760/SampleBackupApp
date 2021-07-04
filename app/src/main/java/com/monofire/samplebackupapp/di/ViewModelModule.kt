package com.monofire.samplebackupapp.di

import com.monofire.samplebackupapp.ui.main.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

// Code with ❤
//┌─────────────────────────────┐
//│ Created by Ömer ATEŞ        │
//│ ─────────────────────────── │
//│ omerates760@gmail.com       │
//│ ─────────────────────────── │
//│ 24.06.2021 - 12:48          │
//└─────────────────────────────┘
val viewModelModule = module {
    viewModel {
        MainViewModel(get(),get())
    }
}