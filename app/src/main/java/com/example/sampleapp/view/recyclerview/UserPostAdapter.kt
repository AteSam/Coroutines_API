package com.example.sampleapp.view.recyclerview

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.sampleapp.repository.model.PostItem
import com.example.sampleapp.repository.model.UserInfo
import com.example.sampleapp.view.recyclerview.viewholder.PostViewHolder
import com.example.sampleapp.view.recyclerview.viewholder.UserPostViewHolder
import com.example.sampleapp.view.recyclerview.viewholder.UserViewHolder

private const val VIEW_TYPE_USER = 1
private const val VIEW_TYPE_POST = 2
private const val VIEW_UNSUPPORTED = 2

class UserPostAdapter : ListAdapter<Any, UserPostViewHolder>(object : DiffUtil.ItemCallback<Any>() {
    override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
        return oldItem == newItem
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
        return oldItem == newItem
    }
}) {
    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is UserInfo -> VIEW_TYPE_USER
            is PostItem -> VIEW_TYPE_POST
            else -> VIEW_UNSUPPORTED
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserPostViewHolder {
        return when (viewType) {
            VIEW_TYPE_USER -> UserViewHolder(parent)
            VIEW_TYPE_POST -> PostViewHolder(parent)
            else -> throw IllegalStateException("Unsupported view type")
        }
    }

    override fun onBindViewHolder(holder: UserPostViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}