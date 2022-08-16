package com.geekbrains.rxjava2dagger2moxy.core.nav

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.geekbrains.rxjava2dagger2moxy.model.GithubUser
import com.geekbrains.rxjava2dagger2moxy.user.UserFragment
import com.geekbrains.rxjava2dagger2moxy.username.UserNameFragment
import com.geekbrains.rxjava2dagger2moxy.username.UserNameFragment.Companion.KEY_USER
import com.github.terrakok.cicerone.androidx.FragmentScreen

object UsersScreen: FragmentScreen{
    override fun createFragment(factory: FragmentFactory): Fragment {
        return UserFragment.getInstance()
    }
}

data class UserScreen(private val user: GithubUser) : FragmentScreen {
    override fun createFragment(factory: FragmentFactory): Fragment {
        return UserNameFragment.newInstance(Bundle().apply {
            putParcelable(KEY_USER, user)
        })
    }
}