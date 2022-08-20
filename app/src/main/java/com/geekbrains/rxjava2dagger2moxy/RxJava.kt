package com.geekbrains.rxjava2dagger2moxy

import io.reactivex.rxjava3.core.Observable
import java.util.concurrent.TimeUnit

private val data = listOf<Int>(1, 2, 34, 685, 23, 53, 3, 2, 2, 45, 34, 1, 2, 90, 53)

fun main(){

    //от и до какого элемента использовать наш поток
    Observable.range(5, 8).subscribe {
        println(it)
    }

}