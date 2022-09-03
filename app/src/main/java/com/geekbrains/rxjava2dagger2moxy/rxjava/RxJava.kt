package com.geekbrains.rxjava2dagger2moxy

import android.util.Log
import io.reactivex.rxjava3.core.*
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import java.util.concurrent.TimeUnit
import kotlin.random.Random

private val news = listOf("ВСУ взяли в плен россиян и разбили технику врага", "Глава МАГАТЭ Гросси обеспокоился состоянием Запорожской АЭС")

fun main(){

    saveCompletable("kisel1989@mail.ru")
        .andThen(getMaybeService())
        .subscribe({
            println("Maybe subscribe")
            println(it)
        },{
            println(it.message)
        }, {
            println("Maybe complete")
        })
}

private fun getMaybeService() = Maybe.create<Boolean>{ emitter ->
    try {
        when(Random.nextInt(3)){
            0 -> emitter.onSuccess(false)
            1 -> emitter.onSuccess(true)
            2 -> emitter.onComplete()
        }
    } catch (e: Exception){
        emitter.onError(e)
        Log.e("SERVICE_ERROR", e.message, e)
    }

}

private fun getNews() = Single.create<List<String>>{ emitter ->
    val result = news
    try {
        emitter.onSuccess(result)
    } catch (e: Exception){
        Log.e("API_ERROR", e.message, e)
        emitter.onError(e)
    }


}

private fun saveCompletable(email: String) = Completable.create { emitter ->
    try {
        saveToDb(email)
        emitter.onComplete()
    } catch (e: Exception){
        Log.e("ERROR", e.message, e)
        emitter.onError(e)
    }

}
private fun saveToDb(email: String){
    Thread.sleep(1000)
    println("Saved $email")
}

