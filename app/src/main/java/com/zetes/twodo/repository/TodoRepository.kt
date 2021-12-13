package com.zetes.twodo.repository

import androidx.annotation.WorkerThread
import com.zetes.twodo.dao.TodoDao
import com.zetes.twodo.entity.Todo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class TodoRepository(private val todoDao: TodoDao) {
    val allTodos: Flow<List<Todo>> = todoDao.getAll()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(todo: Todo) = withContext(Dispatchers.IO) {
        todoDao.insertAll(todo)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun delete(todo: Todo) = withContext(Dispatchers.IO) {
        todoDao.delete(todo)
    }

    //@Suppress("RedundantSuspendModifier")
    //@WorkerThread
    //suspend fun insert(todo: Todo) {
    //    todoDao.insertAll(todo)
    //}

    //@Suppress("RedundantSuspendModifier")
    //@WorkerThread
    //suspend fun delete(todo: Todo) {
    //    todoDao.delete(todo)
    //}
}