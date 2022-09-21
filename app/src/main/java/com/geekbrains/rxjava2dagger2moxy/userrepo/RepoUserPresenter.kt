package com.geekbrains.rxjava2dagger2moxy.userrepo

import com.geekbrains.rxjava2dagger2moxy.core.network.ReposDto
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class RepoUserPresenter(
    private val router: Router, private val repo: ReposDto?,
) : MvpPresenter<RepoUserView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        repo?.let { viewState.showRepo(it) }
    }

    fun onBackPressed(): Boolean {
        router.exit()
        return true
    }
}