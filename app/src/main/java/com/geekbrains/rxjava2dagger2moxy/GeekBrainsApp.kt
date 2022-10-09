package com.geekbrains.rxjava2dagger2moxy

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import com.geekbrains.rxjava2dagger2moxy.core.database.GithubAppDb
import com.geekbrains.rxjava2dagger2moxy.core.di.AppComponent
import com.geekbrains.rxjava2dagger2moxy.core.di.DaggerAppComponent
import com.geekbrains.rxjava2dagger2moxy.core.di.modules.AppModule
import com.geekbrains.rxjava2dagger2moxy.core.utils.ConnectivityListener

class GeekBrainsApp : Application() {

    companion object {
        lateinit var instance: GeekBrainsApp
    }

//    private val cicerone: Cicerone<Router> by lazy {
//        Cicerone.create()
//    }
//
//    val navigationHolder = cicerone.getNavigatorHolder()
//    val router = cicerone.router

    private lateinit var connectivityListener: ConnectivityListener

    lateinit var appComponent: AppComponent

    val database: GithubAppDb by lazy { GithubAppDb.create(this) }

    override fun onCreate() {
        super.onCreate()
        instance = this
        connectivityListener = ConnectivityListener(
            applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

//    fun getConnectObservable() = connectivityListener.status()
//    fun getConnectSingle() = connectivityListener.statusSingle()
}