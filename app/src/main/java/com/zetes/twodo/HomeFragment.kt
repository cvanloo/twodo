package com.zetes.twodo

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.zetes.twodo.databinding.FragmentHomeBinding
import com.zetes.twodo.entity.Todo
import com.zetes.twodo.entity.TodoType
import com.zetes.twodo.recycler.TodoListAdapter
import java.util.*

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    private val todoViewModel: TodoViewModel by viewModels {
        TodoViewModelFactory((activity?.application as TwodoApplication).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)

        // setup recycler view
        val todoData = mutableListOf<Todo>()
        val recyclerView = binding.firstTodoRecyclerVIew
        val todoAdapter = TodoListAdapter(todoData)
        recyclerView.adapter = todoAdapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        val pos =  todoData.size
        todoData.add(Todo(5, "hey", "world", Date(), false, TodoType.Regular))
        todoAdapter.notifyItemInserted(pos)

        todoViewModel.allTodos.observe(this, { todos ->
            todos?.let {
                // Update data
                todoData.clear()
                todoData.addAll(todos)
                todoAdapter.notifyDataSetChanged()

                Log.e("HEY", todoData.size.toString())
            }
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}