package com.geekbrains.rxjava2dagger2moxy.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.geekbrains.rxjava2dagger2moxy.GeekBrainsApp
import com.geekbrains.rxjava2dagger2moxy.R
import com.geekbrains.rxjava2dagger2moxy.core.OnBackPressedListener
import com.geekbrains.rxjava2dagger2moxy.core.utils.makeGone
import com.geekbrains.rxjava2dagger2moxy.core.utils.makeVisible
import com.geekbrains.rxjava2dagger2moxy.databinding.FragmentUserListBinding
import com.geekbrains.rxjava2dagger2moxy.imageconverter.ImageConverterFragment
import com.geekbrains.rxjava2dagger2moxy.model.GithubUser
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class UserFragment : MvpAppCompatFragment(), UserView, OnBackPressedListener {

    companion object {
        fun getInstance(): UserFragment {
            return UserFragment()
        }
    }

    private lateinit var viewBinding: FragmentUserListBinding

    private val adapter = UserAdapter {
        presenter.onItemClicked(it)
    }

    @Inject lateinit var presenter : UserPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        GeekBrainsApp.instance.appComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentUserListBinding.inflate(inflater, container, false).also {
            viewBinding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(viewBinding) {
            rvGithubUsers.layoutManager = LinearLayoutManager(requireContext())
            rvGithubUsers.adapter = adapter
            btnGoToImgConverter.setOnClickListener {
                requireActivity().supportFragmentManager.beginTransaction().replace(
                    R.id.containerMain, ImageConverterFragment()
                ).addToBackStack("").commit()
            }
        }
    }

    override fun initList(list: List<GithubUser>) {
        adapter.users = list
    }

    override fun showLoading() = with(viewBinding) {
        rvGithubUsers.makeGone()
        progressBarUsers.makeVisible()
    }

    override fun hideLoading() = with(viewBinding) {
        rvGithubUsers.makeVisible()
        progressBarUsers.makeGone()
    }

    override fun showError(message: String?) {

    }

    override fun onBackPressed() = presenter.onBackPressed()
}
