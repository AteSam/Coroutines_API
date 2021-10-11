package com.example.sampleapp

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sampleapp.api.PostServiceFactory
import com.example.sampleapp.databinding.ActivityMainBinding
import com.example.sampleapp.repository.UserPostRepositoryImpl
import com.example.sampleapp.view.recyclerview.UserPostAdapter
import com.example.sampleapp.viewmodel.UserPostViewModel

class MainActivity : AppCompatActivity() {
    val factory = object : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (UserPostViewModel::class.java.isAssignableFrom(modelClass)) {
                return UserPostViewModel(
                    UserPostRepositoryImpl(
                        PostServiceFactory.create()
                    )
                ) as T
            }
            throw IllegalArgumentException("view model type $modelClass is not supported")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewBinding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding.root)

        val listAdapter = UserPostAdapter()

        viewBinding.recyclerView.apply {
            layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            adapter = listAdapter
            addItemDecoration(DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL))
        }

        val viewModelUser: UserPostViewModel by viewModels {
            return@viewModels factory
        }
        lifecycleScope.launchWhenStarted {
            viewModelUser.start()
        }
        viewModelUser.users.observe(this) {
            listAdapter.submitList(it.flatMap {
                listOf(it) + it.posts.take(3)
            })
        }
    }
}