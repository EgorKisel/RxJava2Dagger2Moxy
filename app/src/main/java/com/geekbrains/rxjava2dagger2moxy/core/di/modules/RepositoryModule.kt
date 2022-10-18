package com.geekbrains.rxjava2dagger2moxy.core.di.modules

import com.geekbrains.rxjava2dagger2moxy.core.database.UserDAO
import com.geekbrains.rxjava2dagger2moxy.core.network.UsersApi
import com.geekbrains.rxjava2dagger2moxy.core.utils.ConnectivityListener
import com.geekbrains.rxjava2dagger2moxy.repository.GithubRepository
import com.geekbrains.rxjava2dagger2moxy.repository.impl.GithubRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideUserRepository(
        usersApi: UsersApi,
        userDao: UserDAO,
        networkStatus: ConnectivityListener
    ): GithubRepository {
        return GithubRepositoryImpl(usersApi, userDao, networkStatus.statusSingle())
    }
}