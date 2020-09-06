package com.example.appreminder.reciver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.appreminder.utils.convertTimeToDateString
import com.example.appreminder.utils.showNotification

class AlertReceiver:BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        var title = ""
        var content = ""
        var time = 0L
       intent.extras?.let {
            title = it.getString("title").toString()
            content = it.getString("content").toString()
            time = it.getLong("date")
        }

        showNotification(1,context,title + " " + convertTimeToDateString(time) , content)
    }
}