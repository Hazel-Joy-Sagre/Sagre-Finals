package com.example.kotlin_todo_list

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_todo_list.databinding.EntryTodoBinding

// TodoEntityAdapter class inheriting from RecyclerView.Adapter
class TodoEntityAdapter(private val todos: MutableList<TodoEntity>) : RecyclerView.Adapter<TodoEntityAdapter.TodoEntityViewHolder>() {

    private lateinit var binding: EntryTodoBinding // Declare a lateinit variable for binding of type EntryTodoBinding

    // TodoEntityViewHolder class inheriting from RecyclerView.ViewHolder
    class TodoEntityViewHolder(private val binding: EntryTodoBinding) : RecyclerView.ViewHolder(binding.root)
    // Override onCreateViewHolder method
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoEntityViewHolder {
        binding = EntryTodoBinding.inflate(LayoutInflater.from(parent.context), parent, false) // Inflate the item layout using view binding
        return TodoEntityViewHolder(binding) // Return a new instance of TodoEntityViewHolder with the inflated binding
    }
    // Function to add a new todo to the list
    fun addTodo(todoEntity: TodoEntity){
        todos.add(todoEntity) // Add the todo to the list
        notifyItemInserted(todos.size -1) // Notify the adapter that a new item is inserted

    }
    // Function to delete all completed todos from the list
    fun deleteDoneTodos(){
        todos.removeAll{ todo ->
            todo.isChecked // Remove todos that are checked
        }
        notifyDataSetChanged()
    }
    override fun onBindViewHolder(holder: TodoEntityViewHolder, position: Int) {
        val curTodo = todos[position]
        holder.itemView.apply{
            binding.tvTodoEntryTitle.text = curTodo.title
            binding.cbComplete.isChecked = curTodo.isChecked
            toggleStrikeThrough(binding.tvTodoEntryTitle, curTodo.isChecked)
            binding.cbComplete.setOnCheckedChangeListener{_, isChecked ->
                toggleStrikeThrough(binding.tvTodoEntryTitle, isChecked)
                curTodo.isChecked = !curTodo.isChecked
            }
        }
    }

    private fun toggleStrikeThrough(tvTodoEntryTitle: TextView, isChecked: Boolean){
        if(isChecked){
            tvTodoEntryTitle.paintFlags = tvTodoEntryTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        }else{
            tvTodoEntryTitle.paintFlags = tvTodoEntryTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }
    override fun getItemCount(): Int {
        return todos.size
    }

}