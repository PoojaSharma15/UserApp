package com.example.myapplication.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM User_table ORDER BY `First Name` ASC")
    fun getAllUsers(): Flow<List<User>>
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(user: User)
    @Query("SELECT * FROM user_table WHERE Username = :uname ")
    fun requestUser(uname:String): User
}