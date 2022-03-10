package com.datdang.projectbase.di

import com.datdang.projectbase.navigation.fragment.LoginNavigator
import com.datdang.projectbase.navigation.fragment.LoginNavigatorImpl
import com.datdang.projectbase.navigation.fragment.RegisterNavigator
import com.datdang.projectbase.navigation.fragment.RegisterNavigatorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@InstallIn(FragmentComponent::class)
@Module
abstract class NavigatorModule {

    @Binds
    abstract fun loginNavigator(loginNavigator: LoginNavigatorImpl): LoginNavigator


    @Binds
    abstract fun registerNavigator(registerNavigator: RegisterNavigatorImpl): RegisterNavigator

}