package com.datdang.projectbase.data.di

import com.datdang.projectbase.data.repository.UserRepositoryImpl
import com.datdang.projectbase.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository

//    @Binds
//    abstract fun bindFriendRepository(friendRepositoryImpl: FriendRepositoryImpl): FriendRepository
//
//    @Binds
//    abstract fun bindGroupRepository(groupRepositoryImpl: GroupRepositoryImpl): GroupRepository
}