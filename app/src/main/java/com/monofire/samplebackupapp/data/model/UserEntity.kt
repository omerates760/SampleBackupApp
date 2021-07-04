package com.monofire.samplebackupapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

// Code with ❤
//┌─────────────────────────────┐
//│ Created by Ömer ATEŞ        │
//│ ─────────────────────────── │
//│ omerates760@gmail.com       │
//│ ─────────────────────────── │
//│ 27.06.2021 - 08:47          │
//└─────────────────────────────┘
@Entity(tableName = "entity_users")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val userId: Int?=null,
    val name: String
)
