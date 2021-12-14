package com.zetes.twodo

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

private val TAG = ScheduledBroadcastReceiver::class.qualifiedName

class ScheduledBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d(TAG, "Received broadcast")
        Toast.makeText(context, "Hey, Alarm!", Toast.LENGTH_SHORT).show()
    }
}