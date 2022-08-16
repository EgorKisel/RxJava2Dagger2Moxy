package com.geekbrains.rxjava2dagger2moxy.main

import android.os.Bundle
import com.geekbrains.rxjava2dagger2moxy.GeekBrainsApp
import com.geekbrains.rxjava2dagger2moxy.R
import com.geekbrains.rxjava2dagger2moxy.core.OnBackPressedListener
import com.geekbrains.rxjava2dagger2moxy.databinding.ActivityMainBinding
import com.github.terrakok.cicerone.androidx.AppNavigator
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

class MainActivity : MvpAppCompatActivity(), MainView {

    private lateinit var binding: ActivityMainBinding
    private val navigator = AppNavigator(this, R.id.containerMain)

    private val presenter by moxyPresenter { MainPresenter(GeekBrainsApp.instance.router) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        GeekBrainsApp.instance.navigationHolder.setNavigator(navigator)
    }

    override fun onPause() {
        GeekBrainsApp.instance.navigationHolder.removeNavigator()
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