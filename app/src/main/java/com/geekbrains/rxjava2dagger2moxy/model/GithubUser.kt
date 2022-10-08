package com.geekbrains.rxjava2dagger2moxy.model

import android.os.Parcelable
import com.geekbrains.rxjava2dagger2moxy.core.database.RepoDBObject
import com.geekbrains.rxjava2dagger2moxy.core.network.ReposDto
import kotlinx.parcelize.Parcelize

@Parcelize
data class GithubUser(
    val id: Long,
    val login: String,
    val avatarUrl: String?,
    val reposUrl: String?,
    var repos: List<ReposDto>? = null
    ): Parcelable

//@Parcelize
//data class GithubUserRepos(
//    val user: GithubUser,
//    val reposList: List<ReposDto>
//): Parcelable