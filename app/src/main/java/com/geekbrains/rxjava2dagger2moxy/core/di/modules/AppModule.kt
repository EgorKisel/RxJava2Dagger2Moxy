package com.geekbrains.rxjava2dagger2moxy.core.di.modules

import android.content.Context
import android.net.ConnectivityManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val applicationContext: Context) {

    @Singleton
    @Provides
    fun providesContext(): Context {
        return applicationContext
    }

    @Singleton
    @Provides
    fun providesConnectivityManager(): ConnectivityManager {
        return applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }
}