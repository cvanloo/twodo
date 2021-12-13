package com.zetes.twodo.entity

import androidx.room.TypeConverter

class Converters {
    @TypeConverter
    fun toTodoType(value: Int) = enumValues<TodoType>()[value]

    @TypeConverter
    fun fromTodoType(value: TodoType) = value.ordinal
}