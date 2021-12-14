package com.zetes.twodo.controller

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.zetes.twodo.ScheduledBroadcastReceiver

class AlarmScheduler(val context: Context) {

    fun checkPerm() {
        TODO("Not Implemented")
    }

    fun schedule(time: Long) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val receiverIntent = Intent(context, ScheduledBroadcastReceiver::class.java)
        val pendingIntent =
            PendingIntent.getBroadcast(context.applicationContext, 0, receiverIntent, 0)

        // TODO: Check and Ask user for permission!

        alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, time, pendingIntent)
    }

    fun delete(time: Long) {
        TODO("Not Implemented")
    }

    fun schedule(lat: Float, long: Float) {
        TODO("Not Imlpmented")
    }

    fun delete(lat: Float, long: Float) {
        TODO("Not Imlpmented")
    }
}