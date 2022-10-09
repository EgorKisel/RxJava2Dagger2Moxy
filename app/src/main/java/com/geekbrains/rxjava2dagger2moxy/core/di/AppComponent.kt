package com.geekbrains.rxjava2dagger2moxy.core.di

import com.geekbrains.rxjava2dagger2moxy.core.di.modules.*
import com.geekbrains.rxjava2dagger2moxy.details.DetailsPresenter
import com.geekbrains.rxjava2dagger2moxy.details.UserDetailsFragment
import com.geekbrains.rxjava2dagger2moxy.main.MainActivity
import com.geekbrains.rxjava2dagger2moxy.main.MainPresenter
import com.geekbrains.rxjava2dagger2moxy.user.UserFragment
import com.geekbrains.rxjava2dagger2moxy.user.UserPresenter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApiModule::class,
        AppModule::class,
        DatabaseModule::class,
        NavigationModule::class,
        RepositoryModule::class,
    ]
)
interface AppComponent {

    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
    fun inject(usersPresenter: UserPresenter)

    //При выполнении практического задания это должно отсюда уйти
    fun inject(userFragment: UserFragment)
    fun inject(repositoryFragment: UserDetailsFragment)
    fun inject(detailsPresenter: DetailsPresenter)
}