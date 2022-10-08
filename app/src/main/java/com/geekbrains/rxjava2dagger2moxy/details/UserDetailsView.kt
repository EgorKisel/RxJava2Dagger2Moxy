package com.geekbrains.rxjava2dagger2moxy.details

import com.geekbrains.rxjava2dagger2moxy.model.GithubUser
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface UserDetailsView : MvpView {
    fun show(user: GithubUser)
    fun showLoading()
    fun hideLoading()
}
