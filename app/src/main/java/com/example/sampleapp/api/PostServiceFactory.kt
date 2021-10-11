package com.example.sampleapp.api

import retrofit2.Retrofit

class PostServiceFactory(private val retrofit: Retrofit) {
    companion object {
        @JvmStatic
        fun create(): PostService {
            val retrofit = RetrofitFactory.create()
            return retrofit.create(PostService::class.java)
        }
    }
}