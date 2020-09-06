package com.example.appreminder.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.example.appreminder.R

fun showNotification(id:Int,context:Context,title:String,content:String){
    val channelId = "channelReminder"
    val manger = getNotificationManager(context)

    //check for device only available for Oreo and above
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
        NotificationChannel(channelId,"alarm notification",
            NotificationManager.IMPORTANCE_HIGH).apply {
            enableLights(true)
            manger.createNotificationChannel(this)
        }
    }
    //build notification
    val build = NotificationCompat.Builder(context,channelId).apply {
            setSmallIcon(R.drawable.ic_calendar)
            setContentTitle(title)
            setContentText(content)
            priority = NotificationCompat.PRIORITY_HIGH
            color = ContextCompat.getColor(context,R.color.colorOrange)
         }
    //Deliver notification
    manger.notify(id,build.build())
}

private fun getNotificationManager(context: Context):
        NotificationManager =
    context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
