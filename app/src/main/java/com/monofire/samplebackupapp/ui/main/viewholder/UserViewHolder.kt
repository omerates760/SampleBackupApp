package com.monofire.samplebackupapp.ui.main.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.monofire.samplebackupapp.data.model.UserEntity
import com.monofire.samplebackupapp.databinding.ListItemUserBinding

// Code with ❤
//┌─────────────────────────────┐
//│ Created by Ömer ATEŞ        │
//│ ─────────────────────────── │
//│ omerates760@gmail.com       │
//│ ─────────────────────────── │
//│ 27.06.2021 - 11:09          │
//└─────────────────────────────┘
class UserViewHolder(private val binding: ListItemUserBinding) :
    RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun createHolder(parent: ViewGroup): UserViewHolder {
            val binding =
                ListItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return UserViewHolder(binding)
        }
    }

    fun bind(userItem: UserEntity) {
        binding.user = userItem

    }
}