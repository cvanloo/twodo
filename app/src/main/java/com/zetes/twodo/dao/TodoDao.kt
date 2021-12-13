package com.zetes.twodo.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.zetes.twodo.entity.Todo
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {
    @Query("SELECT * FROM todos")
    fun getAll(): Flow<List<Todo>>

    @Query("SELECT * FROM todos WHERE tid IN (:todoIds)")
    fun loadAllByIds(todoIds: IntArray): Flow<List<Todo>>

    @Insert
    fun insertAll(vararg todos: Todo)

    @Delete
    fun delete(todo: Todo)
}