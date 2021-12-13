package com.zetes.twodo

import android.app.Activity
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.format.DateFormat
import android.widget.Button
import android.widget.EditText
import androidx.core.widget.doOnTextChanged

import java.util.*

class NewTodoActivity : AppCompatActivity() {

    companion object {
        val ADD_TODO = "com.zetes.twodo.ADD_TODO"

        val GET_DATE = "com.zetes.twodo.GET_DATE"
        val GET_TITLE = "com.zetes.twodo.GET_TITLE"
        val GET_DESCRIPTION = "com.zetes.twodo.GET_DESCRIPTION"
    }

    private lateinit var btnPickDate: Button
    private lateinit var btnPickTime: Button
    private lateinit var btnAddTodo: Button

    private var mYear: Int = 0
    private var mMonth: Int = 0
    private var mDay: Int = 0
    private var mHour: Int = 0
    private var mMinute: Int = 0
    private var mTitle: String = ""
    private var mDescription: String = ""

    private val datePickerDialogListener =
        DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            mYear = year
            mMonth = month
            mDay = dayOfMonth

            val calendar = Calendar.getInstance()
            calendar.set(mYear, mMonth, mDay)
            btnPickDate.text = "${calendar.get(Calendar.DAY_OF_MONTH)}.${calendar.get(Calendar.MONTH)}.${calendar.get(Calendar.YEAR)}"
        }

    private val timePickerDialogListener =
        TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
            mHour = hourOfDay
            mMinute = minute

            btnPickTime.text = "$mHour:$mMinute"
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_todo)

        // setup action bar
        supportActionBar?.title = "Add Todo"

        // setup buttons
        btnPickDate = findViewById(R.id.btnPickDate)

        btnPickDate.setOnClickListener {
            val calendar = Calendar.getInstance()
            DatePickerDialog(
                this,
                datePickerDialogListener,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        btnPickTime = findViewById(R.id.btnPickTime)

        btnPickTime.setOnClickListener {
            val calendar = Calendar.getInstance()
            TimePickerDialog(
                this,
                timePickerDialogListener,
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                DateFormat.is24HourFormat(this)
            ).show()
        }

        val editTextTitle = findViewById<EditText>(R.id.editTextNewTitle)

        editTextTitle.doOnTextChanged { text, _, _, _ ->
            mTitle = text.toString()
        }

        val editTextDescription = findViewById<EditText>(R.id.editTextNewDescription)

        editTextDescription.doOnTextChanged { text, _, _, _ ->
            mDescription = text.toString()
        }

        btnAddTodo = findViewById(R.id.btnAddTodo)

        btnAddTodo.setOnClickListener {
            val calendar = Calendar.getInstance()
            calendar.set(mYear, mMonth, mDay, mHour, mMinute)

            val date = calendar.timeInMillis
            val intent = Intent()
                .putExtra(GET_DATE, date)
                .putExtra(GET_TITLE, mTitle)
                .putExtra(GET_DESCRIPTION, mDescription)

            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }
}