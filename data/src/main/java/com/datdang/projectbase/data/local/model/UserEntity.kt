package com.datdang.projectbase.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "friend_table")
data class UserEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: String,
    @ColumnInfo(name = "full_name")
    var fullName: String,
    @ColumnInfo(name = "email")
    var email: String
)