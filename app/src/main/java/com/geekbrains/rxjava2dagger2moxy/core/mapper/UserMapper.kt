package com.geekbrains.rxjava2dagger2moxy.core.mapper

import com.geekbrains.rxjava2dagger2moxy.core.database.RepoDBObject
import com.geekbrains.rxjava2dagger2moxy.core.database.UserDbObject
import com.geekbrains.rxjava2dagger2moxy.core.network.ReposDto
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

    fun mapToEntity(dbObject: UserDbObject) : GithubUser {
        return GithubUser(
            id = dbObject.id,
            login = dbObject.login,
            avatarUrl = dbObject.avatarUrl,
            reposUrl = dbObject.reposUrl
        )
    }

    fun mapRepos(repoDto: RepoDBObject): ReposDto {
        return ReposDto(
            id = repoDto.id,
            forksCount = repoDto.forks,
            name = repoDto.name,
            nodeId = repoDto.nodeId,
            createdAt = repoDto.createdAt,
            description = repoDto.description,
            language = repoDto.language,
            updatedAt = repoDto.updatedAt
        )
    }

    fun mapReposToObject(repoDto: ReposDto, mUserId: Long): RepoDBObject {
        return RepoDBObject(
            id = repoDto.id,
            forks = repoDto.forksCount,
            name = repoDto.name,
            userId = mUserId,
            language = repoDto.language,
            description = repoDto.description,
            createdAt = repoDto.createdAt,
            nodeId = repoDto.nodeId,
            updatedAt = repoDto.updatedAt
        )
    }

    fun mapToDBObject(dto: UserDto): UserDbObject{
        return UserDbObject(
            id = dto.id,
            login = dto.login,
            avatarUrl = dto.avatarUrl,
            reposUrl = dto.reposUrl
        )
    }
}