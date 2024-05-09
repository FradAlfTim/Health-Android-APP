package com.example.assignmentproject

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [UserTable::class], version = 1, exportSchema = false)
abstract class UserTableDatabase : RoomDatabase() {
    abstract fun UserTableDAO(): UserTableDAO
    companion object {
        @Volatile
        private var INSTANCE: UserTableDatabase? = null
        fun getDatabase(context: Context): UserTableDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserTableDatabase::class.java,
                    "UserTable_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}

