package com.geekbrains.rxjava2dagger2moxy.details

import android.util.Log
import com.geekbrains.rxjava2dagger2moxy.core.nav.RepoScreen
import com.geekbrains.rxjava2dagger2moxy.core.network.ReposDto
import com.geekbrains.rxjava2dagger2moxy.disposableBy
import com.geekbrains.rxjava2dagger2moxy.model.GithubUserRepos
import com.geekbrains.rxjava2dagger2moxy.repository.GithubRepository
import com.geekbrains.rxjava2dagger2moxy.subscribeByDefault
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter

class DetailsPresenter(
    private val router: Router,
    private val repository: GithubRepository
) : MvpPresenter<UserDetailsView>() {

    private val bag = CompositeDisposable()
    private var mLogin: String? = null

    fun loadUser(login: String) {
        mLogin = login
        Single.zip(repository.getUserByLogin(login),
            repository.getReposByLogin(login)) { user, repos ->
            GithubUserRepos(user, repos.sortedByDescending { it.createdAt })
        }.subscribeByDefault().subscribe({
            viewState.hideLoading()
            viewState.show(it)
        }, {
            Log.d("TAG", it.localizedMessage)
        }).disposableBy(bag)
    }



    fun onBackPressed(): Boolean {
        router.exit()
        return true
    }

    fun openRepoScreen(repo: ReposDto) {
        router.navigateTo(RepoScreen(repo))
    }

    override fun onDestroy() {
        super.onDestroy()
        bag.dispose()
    }
}