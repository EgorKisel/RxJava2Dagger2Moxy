package com.geekbrains.rxjava2dagger2moxy.core.di.modules

import android.content.Context
import com.geekbrains.rxjava2dagger2moxy.core.database.GithubAppDb
import com.geekbrains.rxjava2dagger2moxy.core.database.UserDAO
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object DatabaseModule {

    @Singleton
    @Provides
    fun dataBase(context: Context): GithubAppDb = GithubAppDb.create(context)

    @Singleton
    @Provides
    fun userDao(database: GithubAppDb):UserDAO = database.userDao()
}