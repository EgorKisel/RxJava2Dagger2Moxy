package com.geekbrains.rxjava2dagger2moxy.user

import com.geekbrains.rxjava2dagger2moxy.core.nav.UserScreen
import com.geekbrains.rxjava2dagger2moxy.model.GithubUser
import com.geekbrains.rxjava2dagger2moxy.repository.GithubRepository
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter

class UserPresenter(private val repository: GithubRepository, private val router: Router) :
    MvpPresenter<UserView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.showLoading()
        repository.getUsers().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    viewState.initList(it)
                    viewState.hideLoading()
                },
                {
                    viewState.showError(it.message)
                })
    }

    fun onBackPressed(): Boolean {
        router.exit()
        return true
    }

    fun openUserScreen(user: GithubUser) {
        router.navigateTo(UserScreen(user))
    }
}