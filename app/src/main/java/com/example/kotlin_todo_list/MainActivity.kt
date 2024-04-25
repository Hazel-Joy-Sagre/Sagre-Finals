package com.example.kotlin_todo_list

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin_todo_list.databinding.ActivityMainBinding
import com.example.kotlin_todo_list.ui.theme.Kotlin_ToDo_ListTheme

class MainActivity : ComponentActivity() {

    private lateinit var todoEntityAdapter: TodoEntityAdapter
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        todoEntityAdapter = TodoEntityAdapter(mutableListOf())

        binding.rvTodoEntries.adapter = todoEntityAdapter
        binding.rvTodoEntries.layoutManager = LinearLayoutManager(this)

        binding.btnAddEntry.setOnClickListener{
            val todoEntityTitle = binding.etTodoTitle.text.toString()
            if(todoEntityTitle.isNotEmpty()){
                val todo = TodoEntity(todoEntityTitle)
                todoEntityAdapter.addTodo(todo)
                binding.etTodoTitle.text.clear()
            }
        }
        binding.btnDeleteEntry.setOnClickListener{
            todoEntityAdapter.deleteDoneTodos()
        }
    }
}