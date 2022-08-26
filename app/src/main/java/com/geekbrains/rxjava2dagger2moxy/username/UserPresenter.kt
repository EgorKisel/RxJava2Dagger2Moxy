package com.geekbrains.rxjava2dagger2moxy.username

import com.geekbrains.rxjava2dagger2moxy.model.GithubUser
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class UserPresenter(
    private val router: Router,
    private val user: GithubUser?
): MvpPresenter<UserNameView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setUser(user?.login)
    }

    fun onBackPressed(): Boolean {
        router.exit()
        return true
    }
}