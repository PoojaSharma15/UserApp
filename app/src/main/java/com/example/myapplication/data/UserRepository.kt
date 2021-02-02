package com.example.myapplication.data

import android.util.Log
import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow


class UserRepository(private val userDao: UserDao) {

    val allUsers: Flow<List<User>> = userDao.getAllUsers()
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(user: User) {
        userDao.insert(user)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun requestUser(username: String?) : User? {
        if (username != null) {
            return userDao.requestUser(username)
        }
        else{
            Log.i("Repo","No username recieved")
            return null
        }
    }




}