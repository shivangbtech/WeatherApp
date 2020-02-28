package com.weatherdemoapp.network

import com.weatherdemoapp.model.Todo
import retrofit2.http.GET

/**
 * Created by shivanggoel on 19,February,2020
 */
interface ITodoApi {

    @GET("todos")
    suspend fun getTodoList(): MutableList<Todo>
}