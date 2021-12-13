package com.zetes.twodo.entity

import androidx.room.TypeConverter
import java.util.*

class Converters {
    @TypeConverter
    fun toTodoType(value: Int) = enumValues<TodoType>()[value]

    @TypeConverter
    fun fromTodoType(value: TodoType) = value.ordinal

    @TypeConverter
    fun toDate(timestamp: Long) = Date(timestamp)

    @TypeConverter
    fun fromDate(date: Date) = date.time
}