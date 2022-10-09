package com.geekbrains.rxjava2dagger2moxy.userrepo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.geekbrains.rxjava2dagger2moxy.core.network.ReposDto
import com.geekbrains.rxjava2dagger2moxy.databinding.FragmentUserRepoBinding
import com.geekbrains.rxjava2dagger2moxy.view.BackPressedListener
import com.github.terrakok.cicerone.Router
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class RepoUserFragment: MvpAppCompatFragment(), RepoUserView, BackPressedListener {

    @Inject
    lateinit var router: Router

    private val presenter: RepoUserPresenter by moxyPresenter {
        RepoUserPresenter(router, arguments?.getParcelable(KEY_REPO))
    }

    private lateinit var binding: FragmentUserRepoBinding

    companion object {
        const val KEY_REPO = "KEY_REPO"
        fun getInstance(bundle: Bundle) = RepoUserFragment().apply { arguments = bundle }
    }

    override fun showRepo(repo: ReposDto) {
        with(binding){
            repoId.text = repo.id.toString()
            nodeId.text = repo.nodeId
            name.text = repo.name
            description.text = repo.description
            createdAt.text = repo.createdAt
            updatedAt.text = repo.updatedAt
            language.text = repo.language
            forksCount.text = repo.forksCount.toString()
        }
    }

    override fun onBackPressed() = presenter.onBackPressed()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return FragmentUserRepoBinding.inflate(inflater, container, false).also {
            binding = it
        }.root
    }

}