package com.geekbrains.rxjava2dagger2moxy.repository

import com.geekbrains.rxjava2dagger2moxy.core.database.RepoDBObject
import com.geekbrains.rxjava2dagger2moxy.core.database.UserDAO
import com.geekbrains.rxjava2dagger2moxy.core.database.UserDbObject
import io.reactivex.rxjava3.core.Completable

class RoomCache(private val userDAO: UserDAO): Caching {
    override fun insertUserList(list: List<UserDbObject>): Completable {
        return userDAO.insertAll(list)
    }

    override fun insertRepoList(list: List<RepoDBObject>): Completable {
        return userDAO.insertAllRepos(list)
    }
}