package com.geekbrains.rxjava2dagger2moxy.details

import android.util.Log
import com.geekbrains.rxjava2dagger2moxy.core.nav.RepoScreen
import com.geekbrains.rxjava2dagger2moxy.core.network.ReposDto
import com.geekbrains.rxjava2dagger2moxy.rxjava.disposableBy
import com.geekbrains.rxjava2dagger2moxy.repository.GithubRepository
import com.geekbrains.rxjava2dagger2moxy.rxjava.subscribeByDefault
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter
import javax.inject.Inject

class DetailsPresenter : MvpPresenter<UserDetailsView>() {

    @Inject
    lateinit var repository: GithubRepository
    @Inject
    lateinit var router: Router


    private val bag = CompositeDisposable()
    private var mLogin: String? = null

    fun loadUser(login: String) {
        mLogin = login
        repository.getUserWithReposByLogin(login).subscribeByDefault().subscribe({
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