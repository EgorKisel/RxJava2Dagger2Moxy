package com.geekbrains.rxjava2dagger2moxy

import io.reactivex.rxjava3.core.Observable

private val data = listOf<Int>(1, 2, 34, 685, 23, 53, 3, 2, 2, 45, 34, 1, 2, 90, 53)

fun main(){

    Observable.fromCallable {
        val result = old()
        return@fromCallable result
    }.subscribe{
        println(it)
    }

}

fun old(): List<Boolean> {
    Thread.sleep(2000L)
    return listOf(true, false, true)
}