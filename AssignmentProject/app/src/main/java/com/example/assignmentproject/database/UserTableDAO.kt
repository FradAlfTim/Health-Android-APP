package com.example.assignmentproject.database


import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface UserTableDAO {
    @Query("SELECT * FROM users")
    fun getAllUser(): Flow<List<UserTable>>

    @Query("SELECT * FROM users WHERE name = :name")
    suspend fun getUserByName(name: String): UserTable?

    @Insert
    suspend fun insertUser(subject: UserTable)

    @Update
    suspend fun updateUser(subject: UserTable)

    @Delete
    suspend fun deleteUser(subject: UserTable)
}