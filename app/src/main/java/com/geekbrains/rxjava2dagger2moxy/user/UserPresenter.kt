package com.geekbrains.rxjava2dagger2moxy.user

import com.geekbrains.rxjava2dagger2moxy.core.nav.UserScreen
import com.geekbrains.rxjava2dagger2moxy.model.GithubUser
import com.geekbrains.rxjava2dagger2moxy.repository.GithubRepository
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class UserPresenter(private val repository: GithubRepository, private val router: Router) :
    MvpPresenter<UserView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.initList(repository.getUsers())
    }

    fun onBackPressed(): Boolean{
        router.exit()
        return true
    }

    fun openUserScreen(user: GithubUser) {
        router.navigateTo(UserScreen(user))
    }
}