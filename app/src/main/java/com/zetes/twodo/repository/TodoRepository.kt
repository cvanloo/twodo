package com.zetes.twodo.repository

import androidx.annotation.WorkerThread
import com.zetes.twodo.dao.TodoDao
import com.zetes.twodo.entity.Todo
import kotlinx.coroutines.flow.Flow

class TodoRepository(private val todoDao: TodoDao) {
    val allTodos: Flow<List<Todo>> = todoDao.getAll()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(todo: Todo) {
        todoDao.insertAll(todo)
    }
}