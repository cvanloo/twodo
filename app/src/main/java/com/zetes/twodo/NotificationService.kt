package com.zetes.twodo

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat
import androidx.lifecycle.LifecycleService
import com.zetes.twodo.entity.Todo
import com.zetes.twodo.repository.TodoRepository

class NotificationService : LifecycleService() {

    private lateinit var repository: TodoRepository
    private lateinit var viewModel: TodoViewModel

    override fun onBind(intent: Intent): IBinder? {
        super.onBind(intent)

        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)

        if (intent?.action != null && intent.action.equals(ACTION_STOP)) {
            stopForeground(true)
            stopSelf()
        }

        createNotificationChannel()
        val notification = createNotification(null)
        startForeground(NOTIF_ID, notification)

        repository = (application as TwodoApplication).repository
        viewModel = TodoViewModel(repository)

        viewModel.allTodos.observe(this, { todos ->
            updateNotification(todos)
        })

        return START_STICKY
    }

    private fun createNotificationChannel() {
        val channel = NotificationChannel(
            CHANNEL_ID,
            CHANNEL_NAME,
            NotificationManager.IMPORTANCE_NONE
        ).apply {
            description = "description text"
        }

        channel.lockscreenVisibility = Notification.VISIBILITY_PRIVATE

        val notificationManager: NotificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        notificationManager.createNotificationChannel(channel)
    }

    private fun createNotification(todos: List<Todo>?): Notification {
        val notificationIntent = Intent(this, HomeActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            notificationIntent,
            0
        )

        val notificationBuilder = NotificationCompat.Builder(this, CHANNEL_ID)

        notificationBuilder
            .setContentTitle("Your Todos")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentIntent(pendingIntent)
            .setOngoing(true)
            .setCategory(Notification.CATEGORY_SERVICE)

        if (null != todos && todos.isNotEmpty()) {
            var messageContent = ""
            todos.forEach { todo ->
                messageContent += "${todo.title}\n"
            }
            notificationBuilder.setStyle(NotificationCompat.BigTextStyle().bigText(messageContent))
        } else {
            notificationBuilder.setContentText("Nothing left todo :')")
        }

        return notificationBuilder.build()
    }

    private fun updateNotification(todos: List<Todo>) {
        val notificationManager: NotificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val notification = createNotification(todos)

        notificationManager.notify(NOTIF_ID, notification)
    }

    companion object {
        private const val CHANNEL_NAME = "${BuildConfig.APPLICATION_ID}.REMINDER"
        private const val CHANNEL_ID = "${BuildConfig.APPLICATION_ID}.FOREGROUND_NOTIF_SERVICE"
        private const val NOTIF_ID = 4
        const val ACTION_STOP = "${BuildConfig.APPLICATION_ID}.ACTION_STOP"
    }
}