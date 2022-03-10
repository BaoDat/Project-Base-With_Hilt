package com.datdang.projectbase.di

import com.datdang.projectbase.navigation.activity.ActivityNavigator
import com.datdang.projectbase.navigation.activity.ActivityNavigatorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@InstallIn(ActivityComponent::class)
@Module
abstract class ActivityNavigatorModule {

    @Binds
    abstract fun activityNavigator(activityNavigator: ActivityNavigatorImpl): ActivityNavigator
}