package com.geekbrains.rxjava2dagger2moxy.username

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface UserNameView: MvpView {
    fun setUser(login: String?)
}