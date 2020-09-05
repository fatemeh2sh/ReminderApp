package com.example.appreminder.utils

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import com.example.appreminder.reciver.AlertReceiver

fun setAlarm(context: Context,id:Int, time:Long,title:String,note:String) {

    val alarmManager = getAlarmManger(context)

    val alarmIntent = Intent(context, AlertReceiver::class.java).let { intent ->
        intent.putExtra("date",time)
        intent.putExtra("title",title)
        intent.putExtra("content",note)
        PendingIntent.getBroadcast(context, id, intent, 0)
    }

    //set alarm
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        alarmManager?.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            time,
            alarmIntent)
    }else{
        alarmManager?.setExact(
            AlarmManager.RTC_WAKEUP,
            time,
            alarmIntent
        )
    }
}

fun cancelAlarm(context: Context, id:Int){
    val alarmManager = getAlarmManger(context)

    val alarmIntent = Intent(context, AlertReceiver::class.java).let { intent ->
        PendingIntent.getBroadcast(context, id, intent, PendingIntent.FLAG_NO_CREATE)
    }
    if (alarmIntent != null && alarmManager != null) {
        alarmManager.cancel(alarmIntent)
    }
}

private fun getAlarmManger(context: Context): AlarmManager =
    context.getSystemService(Context.ALARM_SERVICE) as AlarmManager