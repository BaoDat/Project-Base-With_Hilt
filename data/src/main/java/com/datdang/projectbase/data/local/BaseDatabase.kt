package com.datdang.projectbase.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.datdang.projectbase.data.local.dao.UserDao
import com.datdang.projectbase.data.local.model.UserEntity


@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class BaseDatabase : RoomDatabase() {
    // Contain abstract fun Dao about Command–query
    abstract fun userDao(): UserDao
}