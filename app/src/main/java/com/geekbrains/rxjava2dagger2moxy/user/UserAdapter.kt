package com.geekbrains.rxjava2dagger2moxy.user

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.geekbrains.rxjava2dagger2moxy.core.utils.loadImage
import com.geekbrains.rxjava2dagger2moxy.databinding.ItemUserBinding
import com.geekbrains.rxjava2dagger2moxy.model.GithubUser

typealias OnUserClickListener = (login: String) -> Unit

class UserAdapter(
    private val onUserClickListener: OnUserClickListener
) : RecyclerView.Adapter<GithubUserViewHolder>() {

    var users: List<GithubUser> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubUserViewHolder {
        val binding = ItemUserBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return GithubUserViewHolder(binding, onUserClickListener)
    }

    override fun onBindViewHolder(holder: GithubUserViewHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount() = users.size
}

class GithubUserViewHolder(
    private val binding: ItemUserBinding,
    private val onUserClickListener: OnUserClickListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: GithubUser) = with(binding) {
        tvUserLogin.text = item.login
        ivUserAvatar.loadImage(item.avatarUrl)
        root.setOnClickListener {
            onUserClickListener.invoke(item.login)
        }
    }
}