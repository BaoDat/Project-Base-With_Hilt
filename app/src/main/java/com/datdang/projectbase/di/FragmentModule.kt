package com.datdang.projectbase.di

import com.datdang.projectbase.navigation.LoginNavigator
import com.datdang.projectbase.navigation.LoginNavigatorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@InstallIn(FragmentComponent::class)
@Module
abstract class NavigatorModule {

    @Binds
    abstract fun loginNavigator(loginNavigator: LoginNavigatorImpl): LoginNavigator

}