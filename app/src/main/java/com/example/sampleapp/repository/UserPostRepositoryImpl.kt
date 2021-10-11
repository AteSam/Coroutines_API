package com.example.sampleapp.repository

import com.example.sampleapp.api.PostService
import com.example.sampleapp.repository.model.PostItem
import com.example.sampleapp.repository.model.UserInfo

class UserPostRepositoryImpl(private val postService: PostService) : UserPostRepository {
    override suspend fun getListOfUsers(): List<UserInfo> {
        val posts = postService.getPosts()
        val users = postService.getUsers()
        return users.map { user ->
            UserInfo(
                user.id,
                user.name,
                posts.filter { post ->
                    post.userId == user.id
                }.map {
                    PostItem(
                        it.title,
                        it.body
                    )
                }
            )
        }
    }
}