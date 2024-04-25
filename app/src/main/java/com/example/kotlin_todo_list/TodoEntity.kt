package com.example.kotlin_todo_list

data class TodoEntity(
    val title: String,
    var isChecked : Boolean = false
)