package com.example.sampleapp.view.recyclerview.viewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sampleapp.databinding.ItemPostBinding
import com.example.sampleapp.databinding.ItemUserBinding
import com.example.sampleapp.repository.model.PostItem
import com.example.sampleapp.repository.model.UserInfo

sealed class UserPostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(item: Any?)
}

class UserViewHolder(parent: ViewGroup) : UserPostViewHolder(
    ItemUserBinding.inflate(
        LayoutInflater.from(parent.context),
        parent,
        false
    ).root
) {
    private val binding = ItemUserBinding.bind(itemView)

    override fun bind(item: Any?) {
        if (item is UserInfo) {
            binding.userName.text = item.name
            Glide.with(binding.root)
                .load("https://i.pravatar.cc/150?u=$item.id")
                .into(binding.userImage)
        }
    }
}

class PostViewHolder(parent: ViewGroup) : UserPostViewHolder(
    ItemPostBinding.inflate(
        LayoutInflater.from(parent.context),
        parent,
        false
    ).root
) {
    val binding = ItemPostBinding.bind(itemView)

    override fun bind(item: Any?) {
        if (item is PostItem) {
            binding.title.text = item.title
            binding.body.text = item.body
        }
    }
}