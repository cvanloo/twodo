package com.zetes.twodo

import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract
import androidx.appcompat.app.AppCompatActivity
import com.zetes.twodo.entity.Todo
import com.zetes.twodo.entity.TodoType
import java.util.*

class NewTodoActivityContract : ActivityResultContract<String, Todo?>() {

    override fun createIntent(context: Context, input: String): Intent {
        val intent = Intent(context, NewTodoActivity::class.java)
        intent.action = input
        return intent
    }

    override fun parseResult(resultCode: Int, intent: Intent?): Todo? {
        return when (resultCode) {
            AppCompatActivity.RESULT_OK -> {
                intent?.let {
                    val title = intent.getStringExtra(NewTodoActivity.GET_TITLE)
                    val description = intent.getStringExtra(NewTodoActivity.GET_DESCRIPTION)
                    val dueDateTime = Date(intent.getLongExtra(NewTodoActivity.GET_DATE, 0))

                    if (null != title && null != description) {
                        return Todo(0, title, description, dueDateTime, false, TodoType.Regular)
                    }
                }
                return null
            }
            else -> null
        }
    }
}