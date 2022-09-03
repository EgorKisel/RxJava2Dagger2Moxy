package com.geekbrains.rxjava2dagger2moxy.rxjava

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.observables.ConnectableObservable

fun main(){
    val hotObservable = createHotObservable()

    hotObservable.subscribe{
            println("first = $it")
        }

    Thread.sleep(100)

    hotObservable.subscribe{
        println("second = $it")
    }

    hotObservable.connect()
}

fun createHotObservable(): ConnectableObservable<Int> = Observable.just(11, 55, 4, 21)
    .publish()