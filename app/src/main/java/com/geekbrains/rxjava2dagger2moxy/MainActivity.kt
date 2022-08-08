package com.geekbrains.rxjava2dagger2moxy

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.geekbrains.rxjava2dagger2moxy.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), MainView {

    private lateinit var binding: ActivityMainBinding
    private lateinit var presenter: CountersPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initPresenter()

        with(binding){
            btnNumber1.setOnClickListener {
                presenter.onCounterOneClick()
            }
            btnNumber2.setOnClickListener {
                presenter.onCounterTwoClick()
            }
            btnNumber3.setOnClickListener {
                presenter.onCounterThreeClick()
            }
        }
    }

    override fun setText(counter: String, position: Int) {
        with(binding){
            when(position){
                0 -> tvText1.text = counter
                1 -> tvText2.text = counter
                2 -> tvText3.text = counter
            }
        }
    }

    private fun initPresenter(){
        presenter = CountersPresenter(this)
    }
}