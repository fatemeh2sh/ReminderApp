package com.example.appreminder.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_Reminder")
data class Reminder(
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0,
    @ColumnInfo(name = "Alarm_id")
    val alarmId:Int = 0,
    @ColumnInfo(name = "Title")
    val title:String = "",
    @ColumnInfo(name = "Note")
    val note:String = "",
    @ColumnInfo(name = "StartDate")
    val startDate:Long = 0)