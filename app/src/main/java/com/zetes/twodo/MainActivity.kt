package com.zetes.twodo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer

class MainActivity : AppCompatActivity() {

    private val todoViewModel: TodoViewModel by viewModels {
        TodoViewModelFactory((application as TwodoApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        todoViewModel.allTodos.observe(this, Observer { todos ->
            todos?.let {
                // Update data
            }
        })
    }
}