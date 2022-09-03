package com.geekbrains.rxjava2dagger2moxy.imageconverter

import android.net.Uri
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface ImageConverterView: MvpView {

    fun init()

    fun showOriginImage(uri: Uri)

    fun showConvertedImage(uri: Uri)

    fun btnStartConvertEnable()

    fun btnStartConvertDisabled()

    fun btnAbortConvertEnabled()

    fun btnAbortConvertDisabled()

    fun signAbortConvertShow()

    fun signAbortConvertHide()

    fun signGetStartedShow()

    fun signGetStartedHide()

    fun signWaitingShow()

    fun signWaitingHide()

    fun showProgressBar()

    fun hideProgressBar()

    fun showErrorBar()

    fun hideErrorBar()
}