package com.geekbrains.rxjava2dagger2moxy

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable

private val data = listOf<Int>(1, 2, 34, 685, 23, 53, 3, 2, 2, 45, 34, 1, 2, 90, 53)

fun main(){

    val observer = object : Observer<Int>{
        override fun onSubscribe(d: Disposable) = Unit

        override fun onNext(t: Int) {
            println("То что надо: $t")
        }

        override fun onError(e: Throwable) {
            println("Что-то пошло не так!")
        }

        override fun onComplete() {
            println("ВСЁ!")
        }

    }

    val observable = Observable.create<Int>{ emitter ->
        try {
            data.forEach { element ->
                emitter.onNext(element)
            }
        } catch (e: Throwable){
            emitter.onError(e)
        }
        emitter.onComplete()
    }

    observable.subscribe(observer)
}