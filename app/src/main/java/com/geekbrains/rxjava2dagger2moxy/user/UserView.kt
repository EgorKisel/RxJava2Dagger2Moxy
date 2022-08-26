package com.geekbrains.rxjava2dagger2moxy.user

import com.geekbrains.rxjava2dagger2moxy.model.GithubUser
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface UserView: MvpView {
    fun initList(list: List<GithubUser>)
    fun showLoading()
    fun hideLoading()
    fun showError(message: String?)
}

interface ItemClickListener {
    fun onUserClick(user: GithubUser)
}