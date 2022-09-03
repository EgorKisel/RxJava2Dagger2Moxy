package com.geekbrains.rxjava2dagger2moxy

import android.util.Log
import io.reactivex.rxjava3.core.*
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import java.util.concurrent.TimeUnit
import kotlin.random.Random


fun main(){

    saveCompletable("kisel1989@mail.ru")
        .subscribe{
            println("Complete")
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

