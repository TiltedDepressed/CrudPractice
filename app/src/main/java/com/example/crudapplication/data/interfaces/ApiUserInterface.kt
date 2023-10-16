package com.example.crudapplication.data.interfaces

import com.example.crudapplication.data.model.User
import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiUserInterface {

    @GET("users")
    suspend fun getUsers(): List<User>

    @GET("users/{id}")
    suspend fun getUserById(@Path("id") id : Int): User?

    @PUT("users/{id}")
    suspend fun updateUser(@Path("id") id : Int, @Body body : JsonObject): Response<JsonObject>
}