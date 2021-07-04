package com.monofire.samplebackupapp.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.monofire.samplebackupapp.data.model.User
import com.monofire.samplebackupapp.data.model.UserEntity

// Code with ❤
//┌─────────────────────────────┐
//│ Created by Ömer ATEŞ        │
//│ ─────────────────────────── │
//│ omerates760@gmail.com       │
//│ ─────────────────────────── │
//│ 24.06.2021 - 12:24          │
//└─────────────────────────────┘
@Dao
interface BackupDao {
    //Local
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserToLocal(user: UserEntity)

    @Query(value = "DELETE FROM entity_users")
    suspend fun deleteAllUserFromLocal(): Int

    @Query(value = "SELECT * FROM entity_users")
    fun fetchAllUsersFromLocal(): LiveData<List<UserEntity>>

    @Query(value = "SELECT * FROM entity_users")
    fun fetchAllUsersWithoutLiveDataFromLocal(): List<UserEntity>

    //Fake Remote
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUsersToRemote(users: List<User>)


    @Query(value = "SELECT * FROM users")
    suspend fun fetchAllUsersFromRemote(): List<User>


}