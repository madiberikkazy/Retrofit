package com.example.experince2

data class Todo(
    val completed: Boolean,
    var id: Int,
    val title: String,
    val userId: Int
)