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

// MainActivity class inheriting from ComponentActivity
class MainActivity : ComponentActivity() {

    private lateinit var todoEntityAdapter: TodoEntityAdapter // Declare a lateinit variable for todoEntityAdapter of type TodoEntityAdapter
    private lateinit var binding: ActivityMainBinding // Declare a lateinit variable for binding of type ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater) // Inflate the layout using view binding
        val view = binding.root // Get the root view from the binding
        setContentView(view) // Set the content view to the root view

        todoEntityAdapter = TodoEntityAdapter(mutableListOf()) // Initialize the todoEntityAdapter with an empty mutable list

        binding.rvTodoEntries.adapter = todoEntityAdapter // Set the adapter for the RecyclerView to todoEntityAdapter
        binding.rvTodoEntries.layoutManager = LinearLayoutManager(this) // Set the layout manager for the RecyclerView

        binding.btnAddEntry.setOnClickListener{
            val todoEntityTitle = binding.etTodoTitle.text.toString() // Get the text from the todo title EditText
            if(todoEntityTitle.isNotEmpty()){ // Check if the todo title is not empty
                val todo = TodoEntity(todoEntityTitle) // Create a new TodoEntity object with the title
                todoEntityAdapter.addTodo(todo) // Add the todo to the adapter
                binding.etTodoTitle.text.clear() 
            }
        }
        binding.btnDeleteEntry.setOnClickListener{
            todoEntityAdapter.deleteDoneTodos()
        }
    }
}