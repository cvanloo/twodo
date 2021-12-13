package com.zetes.twodo.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo
import androidx.room.TypeConverters
import java.util.Date

@Entity(tableName = "todos")
@TypeConverters(Converters::class)
data class Todo(
    @PrimaryKey(autoGenerate = true) val tid: Int,
    val title: String,
    val description: String?,
    val due: Date,
    val completed: Boolean,
    val type: TodoType
)
