package com.geekbrains.rxjava2dagger2moxy.core.nav

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.geekbrains.rxjava2dagger2moxy.core.network.ReposDto
import com.geekbrains.rxjava2dagger2moxy.details.UserDetailsFragment
import com.geekbrains.rxjava2dagger2moxy.user.UserFragment
import com.geekbrains.rxjava2dagger2moxy.userrepo.RepoUserFragment
import com.geekbrains.rxjava2dagger2moxy.userrepo.RepoUserFragment.Companion.KEY_REPO
import com.github.terrakok.cicerone.androidx.FragmentScreen

object UsersScreen : FragmentScreen {
    override fun createFragment(factory: FragmentFactory): Fragment {
        return UserFragment.getInstance()
    }
}

data class UsersDetailsScreen(private val login: String) : FragmentScreen {
    override fun createFragment(factory: FragmentFactory): Fragment {
        return UserDetailsFragment.getInstance(login)
    }
}

data class RepoScreen(private val repo: ReposDto) : FragmentScreen {
    override fun createFragment(factory: FragmentFactory): Fragment {
        return RepoUserFragment.getInstance(Bundle().apply {
            putParcelable(KEY_REPO,repo)
        })
    }
}
