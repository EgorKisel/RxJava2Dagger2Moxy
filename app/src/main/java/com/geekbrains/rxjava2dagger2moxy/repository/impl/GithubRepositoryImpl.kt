package com.geekbrains.rxjava2dagger2moxy.repository.impl

import com.geekbrains.rxjava2dagger2moxy.model.GithubUser
import com.geekbrains.rxjava2dagger2moxy.repository.GithubRepository

class GithubRepositoryImpl: GithubRepository {

    private val repositories = listOf(
        GithubUser("MrFox"),
        GithubUser("PetrMelnik"),
        GithubUser("Denis"),
        GithubUser("DmitryWb"),
        GithubUser("Anatoly"),
    )

    override fun getUsers(): List<GithubUser>{
        return repositories
    }
}