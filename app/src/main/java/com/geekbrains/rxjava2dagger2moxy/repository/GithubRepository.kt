package com.geekbrains.rxjava2dagger2moxy.repository

import com.geekbrains.rxjava2dagger2moxy.core.network.ReposDto
import com.geekbrains.rxjava2dagger2moxy.model.GithubUser
import io.reactivex.rxjava3.core.Single

interface GithubRepository {

    fun getUsers(): Single<List<GithubUser>>

    fun getUserById(login: String): Single<GithubUser>

    fun getUserByLogin(login: String): Single<GithubUser>

    fun getReposByLogin(login: String): Single<List<ReposDto>>
}