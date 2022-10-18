package com.geekbrains.rxjava2dagger2moxy.user

import com.geekbrains.rxjava2dagger2moxy.core.nav.RepoScreen
import com.geekbrains.rxjava2dagger2moxy.core.nav.UsersDetailsScreen
import com.geekbrains.rxjava2dagger2moxy.core.network.ReposDto
import com.geekbrains.rxjava2dagger2moxy.repository.GithubRepository
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class UserPresenter @Inject constructor(
    private val repository: GithubRepository,
    private val router: Router
) : MvpPresenter<UserView>() {

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

    fun openRepoScreen(repo: ReposDto) {
        router.navigateTo(RepoScreen(repo))
    }

    fun onItemClicked(login: String) {
        router.navigateTo(UsersDetailsScreen(login))
    }
}