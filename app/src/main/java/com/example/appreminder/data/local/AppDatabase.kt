package com.example.appreminder.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.appreminder.data.model.Reminder


@Database(entities = [Reminder::class],
    version = 1,
    exportSchema = true)
abstract class AppDatabase : RoomDatabase() {
    abstract fun reminderDao(): ReminderDao
}