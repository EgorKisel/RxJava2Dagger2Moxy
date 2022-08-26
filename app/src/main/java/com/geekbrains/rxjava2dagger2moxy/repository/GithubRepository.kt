package com.geekbrains.rxjava2dagger2moxy.repository

import com.geekbrains.rxjava2dagger2moxy.model.GithubUser
import io.reactivex.rxjava3.core.Observable

interface GithubRepository {
    fun getUsers(): Observable<List<GithubUser>>
}