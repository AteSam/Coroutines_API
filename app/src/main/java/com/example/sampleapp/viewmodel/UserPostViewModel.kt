package com.example.sampleapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sampleapp.api.PostService
import com.example.sampleapp.api.model.Post
import com.example.sampleapp.repository.UserPostRepository
import com.example.sampleapp.repository.model.UserInfo
import kotlinx.coroutines.launch

class UserPostViewModel(private val repository: UserPostRepository): ViewModel() {
    private val _userPostLiveData = MutableLiveData<List<UserInfo>>()
    val users = _userPostLiveData as LiveData<List<UserInfo>>

    fun start() {
        viewModelScope.launch {
            try {
                _userPostLiveData.postValue(repository.getListOfUsers())
            } catch (e: Exception) {

            }
        }
    }
}