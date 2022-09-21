package com.geekbrains.rxjava2dagger2moxy.userrepo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.geekbrains.rxjava2dagger2moxy.core.network.ReposDto
import com.geekbrains.rxjava2dagger2moxy.databinding.RepoItemBinding

typealias OnUserClickListener = (repo: ReposDto) -> Unit

class RepoAdapter(private val onUserClickListener: OnUserClickListener) :
    RecyclerView.Adapter<GithubUserRepoViewHolder>() {

    var repos: List<ReposDto> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubUserRepoViewHolder {
        return GithubUserRepoViewHolder(
            RepoItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ), onUserClickListener
        )
    }

    override fun onBindViewHolder(holder: GithubUserRepoViewHolder, position: Int) {
        holder.bind(repos[position])
    }

    override fun getItemCount(): Int {
        return repos.size
    }
}

class GithubUserRepoViewHolder(
    private val binding: RepoItemBinding,
    private val onUserClickListener: OnUserClickListener,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: ReposDto) = with(binding) {

        nameRepo.text = item.name

        dateCreating.text = item.createdAt

        root.setOnClickListener {
            onUserClickListener.invoke(item)
        }
    }
}