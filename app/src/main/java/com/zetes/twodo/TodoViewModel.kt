package com.zetes.twodo

import androidx.lifecycle.*
import com.zetes.twodo.entity.Todo
import com.zetes.twodo.repository.TodoRepository
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class TodoViewModel(private val repository: TodoRepository) : ViewModel() {
    val allTodos: LiveData<List<Todo>> = repository.allTodos.asLiveData()

    fun insert(todo: Todo) = viewModelScope.launch {
        repository.insert(todo)
    }
}

class TodoViewModelFactory(private val repository: TodoRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TodoViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TodoViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}