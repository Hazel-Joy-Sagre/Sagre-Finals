package com.example.kotlin_todo_list

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_todo_list.databinding.EntryTodoBinding

class TodoEntityAdapter(private val todos: MutableList<TodoEntity>) : RecyclerView.Adapter<TodoEntityAdapter.TodoEntityViewHolder>() {

    private lateinit var binding: EntryTodoBinding

    class TodoEntityViewHolder(private val binding: EntryTodoBinding) : RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoEntityViewHolder {
        binding = EntryTodoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TodoEntityViewHolder(binding)
    }
    fun addTodo(todoEntity: TodoEntity){
        todos.add(todoEntity)
        notifyItemInserted(todos.size -1)

    }
    fun deleteDoneTodos(){
        todos.removeAll{ todo ->
            todo.isChecked
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