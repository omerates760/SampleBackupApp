package com.monofire.samplebackupapp.data.repository

import com.monofire.samplebackupapp.data.local.dao.BackupDao
import com.monofire.samplebackupapp.data.model.User
import com.monofire.samplebackupapp.data.model.UserEntity

// Code with ❤
//┌─────────────────────────────┐
//│ Created by Ömer ATEŞ        │
//│ ─────────────────────────── │
//│ omerates760@gmail.com       │
//│ ─────────────────────────── │
//│ 24.06.2021 - 12:42          │
//└─────────────────────────────┘
class UserRepository(private val dao: BackupDao) {

    fun fetchAllUsersFromLocal() = dao.fetchAllUsersFromLocal()
    fun fetchAllUsersWithoutLiveDataFromLocal() = dao.fetchAllUsersWithoutLiveDataFromLocal()

    suspend fun insertUserToLocal(user: UserEntity) = dao.insertUserToLocal(user)

    fun insertUsersToRemote(userList: List<UserEntity>) =
        dao.insertUsersToRemote(userEntityMappingToUser(userList))

    suspend fun fetchAllUsersFromRemote() = userMappingToUserEntity(dao.fetchAllUsersFromRemote())

    suspend fun deleteAllUserFromLocal() = dao.deleteAllUserFromLocal()

    private fun userEntityMappingToUser(users: List<UserEntity>): List<User> {
        return users.map {
            User(name = it.name)
        }.toMutableList()
    }
    private fun userMappingToUserEntity(users: List<User>): List<UserEntity> {
        return users.map {
            UserEntity(name = it.name)
        }.toMutableList()
    }

}