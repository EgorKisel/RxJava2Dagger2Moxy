package com.geekbrains.rxjava2dagger2moxy.rxjava

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.observables.ConnectableObservable
import io.reactivex.rxjava3.schedulers.TestScheduler
import java.util.concurrent.TimeUnit

fun main(){
    val hotObservable = createHotObservable()

    hotObservable.subscribe{
            println("first = $it")
        }

    hotObservable.connect()
}

fun createHotObservable(): ConnectableObservable<Int> {

    return Observable.just(1, 12, 23, 34, 45)
        .map { it.also { println(it) } }
        .publish()
}
