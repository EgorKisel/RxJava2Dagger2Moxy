package com.geekbrains.rxjava2dagger2moxy.rxjava

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.observables.ConnectableObservable


fun main(){
    val hotObservable = createHotObservable()

    hotObservable.subscribe{
            println("first = $it")
        }

   // hotObservable.connect()
}

fun createHotObservable(): Observable<Int> {

    return Observable.just(1, 12, 23, 34, 45)
        .map { it.also { println(it) } }
        .publish()
        .cache()
}
