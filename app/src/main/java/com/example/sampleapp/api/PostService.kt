package com.example.sampleapp.api

import com.example.sampleapp.api.model.Post
import com.example.sampleapp.api.model.User
import retrofit2.http.GET

interface PostService {
    @GET("/posts")
    suspend fun getPosts(): List<Post>

    @GET("/users")
    suspend fun getUsers(): List<User>
}