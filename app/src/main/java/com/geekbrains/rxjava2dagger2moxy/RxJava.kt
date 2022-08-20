package com.geekbrains.rxjava2dagger2moxy

import io.reactivex.rxjava3.core.Observable
import kotlin.random.Random

private val data = listOf<Int>(1, 2, 34, 685, 23, 53, 3, 2, 2, 45, 34, 1, 2, 90, 53)

fun main(){

    val observableNames = Observable.just("Egor", "Vitaliy", "Galia")
    val observableMail = Observable.just("Egor@mail.ru", "Vitaliy@yandex.ru", "Galia@gmail.com")

    observableNames
        .flatMap { element ->
            //val delay = Random.nextInt(1000)
            return@flatMap getUserInfo(element)
        }.subscribe{
        println(it)
    }
//
//    Observable.fromIterable(data)
//        .filter{
//            it > 34
//        }
//        .subscribe{
//            println(it)
//    }
}

private fun getUserInfo(name: String): Observable<List<String>>{
    return Observable.just(listOf(name, "email.com"))
}
