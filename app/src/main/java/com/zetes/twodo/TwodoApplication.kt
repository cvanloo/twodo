package com.zetes.twodo

import android.app.Application
import com.zetes.twodo.dao.AppDatabase
import com.zetes.twodo.repository.TodoRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class TwodoApplication : Application() {
    val applicationScope = CoroutineScope(SupervisorJob())
    val db by lazy { AppDatabase.getDatabase(applicationContext, applicationScope) }
    val repository by lazy { TodoRepository(db.todoDao()) }
}