package com.geekbrains.rxjava2dagger2moxy.repository

import com.geekbrains.rxjava2dagger2moxy.model.GithubUser

interface GithubRepository {
    fun getUsers():List<GithubUser>
}