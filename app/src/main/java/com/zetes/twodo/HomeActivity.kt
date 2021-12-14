package com.zetes.twodo

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.zetes.twodo.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityHomeBinding

    private val todoViewModel: TodoViewModel by viewModels {
        TodoViewModelFactory((application as TwodoApplication).repository)
    }

    private val fetchTodo = registerForActivityResult(NewTodoActivityContract()) { todo ->
        todo?.let {
            todoViewModel.insert(todo)

            val alarmManager: AlarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val receiverIntent = Intent(this, ScheduledBroadcastReceiver::class.java)
            val pendingIntent = PendingIntent.getBroadcast(applicationContext, 0, receiverIntent, 0)

            // TODO: Check and Ask user for permission!

            alarmManager.setExactAndAllowWhileIdle(
                AlarmManager.RTC_WAKEUP,
                todo.due.time,
                pendingIntent
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_home)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.fab.setOnClickListener { view ->
            fetchTodo.launch(NewTodoActivity.ADD_TODO)
        }

        // start the foreground notification service
        val notifIntent = Intent(this, NotificationService::class.java)
        startForegroundService(notifIntent)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_home)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}