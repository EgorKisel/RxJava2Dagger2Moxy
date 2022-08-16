package com.geekbrains.rxjava2dagger2moxy.username

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.geekbrains.rxjava2dagger2moxy.GeekBrainsApp
import com.geekbrains.rxjava2dagger2moxy.core.OnBackPressedListener
import com.geekbrains.rxjava2dagger2moxy.databinding.FragmentUserNameBinding
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserNameFragment: MvpAppCompatFragment(), UserNameView, OnBackPressedListener {

    private val presenter: UserPresenter by moxyPresenter {
        UserPresenter(GeekBrainsApp.instance.router, arguments?.getParcelable(KEY_USER))
    }

    private lateinit var binding: FragmentUserNameBinding

    companion object {
        const val KEY_USER = "KEY_USER"
        fun newInstance(bundle: Bundle) = UserNameFragment().apply { arguments = bundle }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return FragmentUserNameBinding.inflate(inflater, container, false).also {
            binding = it
        }.root
    }

    override fun onBackPressed() = presenter.onBackPressed()


    override fun setUser(login: String?) {
        binding.userName.text = login
    }
}