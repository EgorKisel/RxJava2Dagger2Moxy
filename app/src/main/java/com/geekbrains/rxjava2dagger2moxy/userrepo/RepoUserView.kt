package com.geekbrains.rxjava2dagger2moxy.userrepo

import com.geekbrains.rxjava2dagger2moxy.core.network.ReposDto
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface RepoUserView: MvpView {
    fun showRepo(repo: ReposDto)
}