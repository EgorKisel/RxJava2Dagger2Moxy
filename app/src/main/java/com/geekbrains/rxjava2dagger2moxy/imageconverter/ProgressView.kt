package com.geekbrains.rxjava2dagger2moxy.imageconverter

import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface ProgressView {
    fun showProgressBar()

    fun hideProgressBar()
}