package com.geekbrains.rxjava2dagger2moxy.main

import android.os.Bundle
import com.geekbrains.rxjava2dagger2moxy.GeekBrainsApp
import com.geekbrains.rxjava2dagger2moxy.R
import com.geekbrains.rxjava2dagger2moxy.core.OnBackPressedListener
import com.geekbrains.rxjava2dagger2moxy.databinding.ActivityMainBinding
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.androidx.AppNavigator
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class MainActivity : MvpAppCompatActivity(), MainView {

    private lateinit var binding: ActivityMainBinding
    private val navigator = AppNavigator(this, R.id.containerMain)
    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    private val presenter by moxyPresenter {
        MainPresenter().apply {
            GeekBrainsApp.instance.appComponent.inject(this)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        GeekBrainsApp.instance.appComponent.inject(this)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach { currentFragment ->
            if (currentFragment is OnBackPressedListener && currentFragment.onBackPressed()){
                return
            }
        }
        presenter.onBackPressed()
    }
}