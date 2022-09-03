package com.geekbrains.rxjava2dagger2moxy.core.utils

import android.content.Context
import android.net.ConnectivityManager
import io.reactivex.rxjava3.subjects.PublishSubject

class ConnectivityListener(context: Context) {

    private val subject = PublishSubject.create<Boolean>()

    init {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
       // connectivityManager.requestNetwork()
    }
}