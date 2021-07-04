package com.monofire.samplebackupapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

// Code with ❤
//┌─────────────────────────────┐
//│ Created by Ömer ATEŞ        │
//│ ─────────────────────────── │
//│ omerates760@gmail.com       │
//│ ─────────────────────────── │
//│ 24.06.2021 - 12:25          │
//└─────────────────────────────┘
@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true)
    val userId: Int?=null,
    val name: String
)
