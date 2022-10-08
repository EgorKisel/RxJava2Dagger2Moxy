package com.geekbrains.rxjava2dagger2moxy.repository

import com.geekbrains.rxjava2dagger2moxy.core.database.RepoDBObject
import com.geekbrains.rxjava2dagger2moxy.core.database.UserDbObject
import io.reactivex.rxjava3.core.Completable

interface Caching {

    fun insertUserList(list: List<UserDbObject>): Completable

    fun insertRepoList(list: List<RepoDBObject>): Completable

}