package com.geekbrains.rxjava2dagger2moxy.core.di.modules

import com.geekbrains.rxjava2dagger2moxy.repository.GithubRepository
import com.geekbrains.rxjava2dagger2moxy.user.UserPresenter
import com.github.terrakok.cicerone.Router
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PresenterModule {

    @Singleton
    @Provides
    fun provideUserPresenter(
        repository: GithubRepository,
        router: Router
    ): UserPresenter {
        return UserPresenter(repository, router)
    }
}