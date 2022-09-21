package com.geekbrains.rxjava2dagger2moxy.repository.impl

import com.geekbrains.rxjava2dagger2moxy.core.mapper.UserMapper
import com.geekbrains.rxjava2dagger2moxy.core.mapper.UserMapper.mapToEntity
import com.geekbrains.rxjava2dagger2moxy.core.network.ReposDto
import com.geekbrains.rxjava2dagger2moxy.core.network.UsersApi
import com.geekbrains.rxjava2dagger2moxy.model.GithubUser
import com.geekbrains.rxjava2dagger2moxy.repository.GithubRepository
import io.reactivex.rxjava3.core.Single
import java.util.concurrent.TimeUnit

class GithubRepositoryImpl constructor(
    private val usersApi: UsersApi
) : GithubRepository {
    override fun getUsers(): Single<List<GithubUser>> {
        return usersApi.getAllUsers()
            .map { it.map(UserMapper::mapToEntity) }.delay(200, TimeUnit.MILLISECONDS)
    }

    override fun getUserById(login: String): Single<GithubUser> {
        return usersApi.getUser(login)
            .map(UserMapper::mapToEntity)
    }

    override fun getUserByLogin(login: String): Single<GithubUser> {
        return usersApi.getUser(login).map(::mapToEntity).delay(200, TimeUnit.MILLISECONDS)
    }

    override fun getReposByLogin(login: String): Single<List<ReposDto>> {
        return usersApi.getRepos(login)
    }
}