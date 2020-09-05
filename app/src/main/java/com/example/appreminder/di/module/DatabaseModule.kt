package com.example.appreminder.di.module

import android.app.Application
import androidx.room.Room
import com.example.appreminder.data.local.AppDatabase
import com.example.appreminder.data.local.ReminderDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
class DatabaseModule{

    @Provides
    @Singleton
    fun provideDb(context: Application):AppDatabase{
        return Room.databaseBuilder<AppDatabase>(
            context.applicationContext,
            AppDatabase::class.java,
            "Db_Reminder")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    @Singleton
    fun getReminder(appDatabase: AppDatabase):ReminderDao{
        return appDatabase.reminderDao()
    }
}