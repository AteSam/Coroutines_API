package com.example.sampleapp.repository

import com.example.sampleapp.repository.model.UserInfo

interface UserPostRepository {
    suspend fun getListOfUsers(): List<UserInfo>
}