package com.example.experince2

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query


interface ToDoApi {
     @GET("/todos")
     suspend fun getTodos():Response<List<Todo>>
}