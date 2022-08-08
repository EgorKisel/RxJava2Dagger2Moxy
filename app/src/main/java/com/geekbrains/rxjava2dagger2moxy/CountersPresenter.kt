package com.geekbrains.rxjava2dagger2moxy

class CountersPresenter(private val view: MainView) {

    private val model = CountersModel()

    companion object{
        const val COUNTER_ONE = 0
        const val COUNTER_TWO = 1
        const val COUNTER_THREE = 2
    }

    fun onCounterOneClick(){
        view.setText(model.next(COUNTER_ONE).toString(), COUNTER_ONE)
    }

    fun onCounterTwoClick(){
        view.setText(model.next(COUNTER_TWO).toString(), COUNTER_TWO)
    }

    fun onCounterThreeClick(){
        view.setText(model.next(COUNTER_THREE).toString(), COUNTER_THREE)
    }
}