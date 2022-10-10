package com.geekbrains.rxjava2dagger2moxy.details

import android.os.Bundle
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.geekbrains.rxjava2dagger2moxy.GeekBrainsApp
import com.geekbrains.rxjava2dagger2moxy.core.database.AndroidNetworkStatus
import com.geekbrains.rxjava2dagger2moxy.core.network.NetworkProvider
import com.geekbrains.rxjava2dagger2moxy.databinding.FragmentUserNameBinding
import com.geekbrains.rxjava2dagger2moxy.model.GithubUser
import com.geekbrains.rxjava2dagger2moxy.repository.impl.GithubRepositoryImpl
import com.geekbrains.rxjava2dagger2moxy.user.hide
import com.geekbrains.rxjava2dagger2moxy.user.loadGlide
import com.geekbrains.rxjava2dagger2moxy.user.show
import com.geekbrains.rxjava2dagger2moxy.userrepo.RepoAdapter
import com.geekbrains.rxjava2dagger2moxy.view.BackPressedListener
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter


class UserDetailsFragment : MvpAppCompatFragment(), UserDetailsView, BackPressedListener {

    private val reposAdapter = RepoAdapter {
        presenter.openRepoScreen(it)
    }

    companion object {
        private const val ARG_LOGIN = "ARG_LOGIN"

        fun getInstance(login: String): UserDetailsFragment {
            return UserDetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_LOGIN, login)
                }
            }
        }
    }

    private var viewBinding: FragmentUserNameBinding? = null

    private val presenter: DetailsPresenter by moxyPresenter {
        DetailsPresenter().apply {
            GeekBrainsApp.instance.appComponent.inject(this)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        GeekBrainsApp.instance.appComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentUserNameBinding.inflate(inflater, container, false).also {
            viewBinding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getString(ARG_LOGIN)?.let {
            presenter.loadUser(it)
        }
        viewBinding?.rvGithubUserRepo?.adapter = reposAdapter
        viewBinding?.rvGithubUserRepo?.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun show(user: GithubUser) {
        TransitionManager.beginDelayedTransition(viewBinding?.root)
        viewBinding?.userName?.text = user.login
        viewBinding?.ivUserAvatar?.loadGlide(user.avatarUrl)
        user.repos?.let {
            reposAdapter.repos = it
        }
    }

    override fun showLoading() {
        viewBinding?.apply {
            progress.show()
            userName.hide()
            ivUserAvatar.hide()
            rvGithubUserRepo.hide()
        }
    }

    override fun hideLoading() {
        viewBinding?.apply {
            progress.hide()
            userName.show()
            ivUserAvatar.show()
            rvGithubUserRepo.show()
        }
    }

    override fun onBackPressed() = presenter.onBackPressed()
}