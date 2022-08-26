package com.geekbrains.rxjava2dagger2moxy.repository.impl

import com.geekbrains.rxjava2dagger2moxy.model.GithubUser
import com.geekbrains.rxjava2dagger2moxy.repository.GithubRepository
import io.reactivex.rxjava3.core.Observable
import java.util.concurrent.TimeUnit

class GithubRepositoryImpl: GithubRepository {

    private val repositories = listOf(
        GithubUser("MrFox"),
        GithubUser("PetrMelnik"),
        GithubUser("Denis"),
        GithubUser("DmitryWb"),
        GithubUser("Anatoly"),
    )

    override fun getUsers(): Observable<List<GithubUser>> {
        return Observable.fromIterable(listOf(repositories)).delay(1, TimeUnit.MILLISECONDS)
    }
}