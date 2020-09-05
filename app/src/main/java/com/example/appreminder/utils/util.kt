package com.example.appreminder.utils

import android.content.Context
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.example.appreminder.R
import java.util.*


fun convertTimeToDateString(time: Long):String{
   var calendar = Calendar.getInstance()
    calendar.timeInMillis = time

    var newDate = StringBuilder()
    newDate.append(calendar.get(Calendar.YEAR))
    newDate.append("/")
    newDate.append(calendar.get(Calendar.MONTH))
    newDate.append("/")
    newDate.append(calendar.get(Calendar.DAY_OF_MONTH))
    newDate.append(" ")
    newDate.append(calendar.get(Calendar.HOUR_OF_DAY))
    newDate.append(":")
    newDate.append(calendar.get(Calendar.MINUTE))

    return newDate.toString()
}

fun convertTimeDate(time: Long):Date{
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = time
    return calendar.time
}

fun convertDateToTime(date: Date):Long{
    val calendar = Calendar.getInstance()
    calendar.time = date
    return calendar.timeInMillis
}

fun fadeInAnimation(context: Context):Animation{
    return AnimationUtils.loadAnimation(
        context.applicationContext,
        R.anim.fade_in
    )
}

fun fadeOutAnimation(context: Context):Animation{
    return AnimationUtils.loadAnimation(
        context.applicationContext,
        R.anim.fade_out
    )
}