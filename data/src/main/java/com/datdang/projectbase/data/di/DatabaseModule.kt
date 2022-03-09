package com.datdang.projectbase.data.di

import android.app.Application
import androidx.room.Room
import com.datdang.projectbase.data.local.BaseDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(application: Application): BaseDatabase {
        return Room.databaseBuilder(application, BaseDatabase::class.java, "base_database")
            .fallbackToDestructiveMigration()
            .build()
    }

}