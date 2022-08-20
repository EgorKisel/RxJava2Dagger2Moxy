package com.geekbrains.rxjava2dagger2moxy

import io.reactivex.rxjava3.core.Observable

private val data = listOf<Int>(1, 2, 34, 685, 23, 53, 3, 2, 2, 45, 34, 1, 2, 90, 53)

fun main(){

    //оператор filter
    Observable.fromIterable(data)
        .filter{
            it > 34
        }
        .subscribe{
            println(it)
    }

}
