package com.geekbrains.rxjava2dagger2moxy.imageconverter

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.geekbrains.rxjava2dagger2moxy.GeekBrainsApp
import com.geekbrains.rxjava2dagger2moxy.databinding.FragmentImageConverterBinding
import com.github.terrakok.cicerone.Router
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class ImageConverterFragment : MvpAppCompatFragment(), ImageConverterView, BackButtonListener {

    @Inject
    lateinit var router: Router

    private var _vb: FragmentImageConverterBinding? = null
    private val vb get() = _vb!!
    private var imageUri: Uri? = null
    private val presenter: ImageConverterPresenter by moxyPresenter {
        ImageConverterPresenter(
            ConverterJpgToPng(requireContext()),
            MySchedulersFactory.create(),
            router
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentImageConverterBinding.inflate(inflater, container, false).also { _vb = it }.root

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == 1000) {
            imageUri = data?.data
            imageUri?.let { presenter.originalImageSelected(it) }
        }
    }

    override fun backPressed(): Boolean = presenter.backPressed()

    override fun init() {
        hideProgressBar()
        hideErrorBar()
        btnStartConvertDisabled()
        btnAbortConvertDisabled()
        signGetStartedShow()
        signAbortConvertHide()
        signWaitingShow()
        vb.btnImageSelection.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/jpg"
            startActivityForResult(intent, 1000)
        }
        vb.btnStartConverting.setOnClickListener {
            imageUri?.let(presenter::startConvertingPressed)
        }
        vb.btnAbort.setOnClickListener {
            presenter.abortConvertImagePressed()
        }
    }

    override fun showOriginImage(uri: Uri) {
        vb.imgViewOriginalImg.setImageURI(uri)
    }

    override fun showConvertedImage(uri: Uri) {
        vb.imgViewConvertedImg.setImageURI(uri)
    }

    override fun showProgressBar() {
        vb.progressBar2.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        vb.progressBar2.visibility = View.GONE
    }

    override fun showErrorBar() {
        vb.imgViewConvertedImg.setImageURI(null)
        vb.imgViewErrorSign.visibility = View.VISIBLE
    }

    override fun hideErrorBar() {
        vb.imgViewErrorSign.visibility = View.GONE
    }

    override fun btnStartConvertEnable() {
        vb.btnStartConverting.isEnabled = true
    }

    override fun btnStartConvertDisabled() {
        vb.btnStartConverting.isEnabled = false
    }

    override fun btnAbortConvertEnabled() {
        vb.btnAbort.isEnabled = true
    }

    override fun btnAbortConvertDisabled() {
        vb.btnAbort.isEnabled = false
    }

    override fun signAbortConvertShow() {
        vb.imgViewConvertedImg.setImageURI(null)
        vb.imgViewCancelSign.visibility = View.VISIBLE
    }

    override fun signAbortConvertHide() {
        vb.imgViewCancelSign.visibility = View.GONE
    }

    override fun signGetStartedShow() {
        vb.imgViewGetStartedSign.visibility = View.VISIBLE
    }

    override fun signGetStartedHide() {
        vb.imgViewGetStartedSign.visibility = View.GONE
    }

    override fun signWaitingShow() {
        vb.imgViewConvertedImg.setImageURI(null)
        vb.imgViewWaitingSign.visibility = View.VISIBLE
    }

    override fun signWaitingHide() {
        vb.imgViewWaitingSign.visibility = View.GONE
    }

    companion object{
        fun getInstance(): ImageConverterFragment {
            return ImageConverterFragment()
        }
    }
}