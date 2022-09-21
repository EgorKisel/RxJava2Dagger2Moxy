package com.geekbrains.rxjava2dagger2moxy.core.mapper

import com.geekbrains.rxjava2dagger2moxy.core.network.UserDto
import com.geekbrains.rxjava2dagger2moxy.model.GithubUser

object UserMapper {

    fun mapToEntity(dto: UserDto) : GithubUser {
        return GithubUser(
            id = dto.id,
            login = dto.login,
            avatarUrl = dto.avatarUrl,
            reposUrl = dto.reposUrl
        )
    }
}