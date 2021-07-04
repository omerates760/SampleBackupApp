package com.monofire.samplebackupapp.ui.main.adapter

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.monofire.samplebackupapp.data.model.UserEntity
import com.monofire.samplebackupapp.ui.main.viewholder.UserViewHolder

// Code with ❤
//┌─────────────────────────────┐
//│ Created by Ömer ATEŞ        │
//│ ─────────────────────────── │
//│ omerates760@gmail.com       │
//│ ─────────────────────────── │
//│ 27.06.2021 - 11:06          │
//└─────────────────────────────┘
class UserAdapter() :
    RecyclerView.Adapter<UserViewHolder>() {
    private val users = mutableListOf<UserEntity>()

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newUsers: List<UserEntity>) {
        users.apply {
            clear()
            addAll(newUsers)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder.createHolder(parent)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val product = users[position]
        holder.bind(product)
    }

    override fun getItemCount(): Int = users.size
}