package com.geekbrains.rxjava2dagger2moxy.user

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.geekbrains.rxjava2dagger2moxy.R
import com.geekbrains.rxjava2dagger2moxy.model.GithubUser

class UserAdapter() : RecyclerView.Adapter<GithubUserViewHolder>() {

    private lateinit var userClick: ItemClickListener

    fun setOnUserClickListener(listener: ItemClickListener) {
        userClick = listener
    }

    var users: List<GithubUser> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubUserViewHolder {
        return GithubUserViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_user, parent, false), userClick)
    }

    override fun onBindViewHolder(holder: GithubUserViewHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount() = users.size

}

class GithubUserViewHolder(itemView: View, private val userClick: ItemClickListener) :
    RecyclerView.ViewHolder(itemView) {

    private val tvLogin by lazy { itemView.findViewById<TextView>(R.id.tvUserLogin) }

    fun bind(item: GithubUser) = with(item) {
        tvLogin.text = item.login
        itemView.setOnClickListener {
            Log.d("TAG", "bind() called")
            userClick.onUserClick(item)
        }
    }
}