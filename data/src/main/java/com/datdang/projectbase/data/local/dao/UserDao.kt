package com.datdang.projectbase.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.datdang.projectbase.data.local.model.UserEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe

@Dao
interface UserDao {

    @Query("SELECT * FROM user_table WHERE email = :email")
    fun getFriendByEmail(email: String): Maybe<UserEntity>

    @Insert
    fun insertFriends(friends: List<UserEntity>): Completable

    @Query("DELETE FROM user_table")
    fun clearFriends(): Completable
}