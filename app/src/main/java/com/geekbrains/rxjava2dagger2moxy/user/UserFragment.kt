package com.geekbrains.rxjava2dagger2moxy.user

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.geekbrains.rxjava2dagger2moxy.GeekBrainsApp
import com.geekbrains.rxjava2dagger2moxy.core.OnBackPressedListener
import com.geekbrains.rxjava2dagger2moxy.databinding.FragmentUserListBinding
import com.geekbrains.rxjava2dagger2moxy.model.GithubUser
import com.geekbrains.rxjava2dagger2moxy.repository.impl.GithubRepositoryImpl
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserFragment: MvpAppCompatFragment(), UserView, OnBackPressedListener{

    companion object{
        fun getInstance(): UserFragment{
            return UserFragment()
        }
    }

    private val bag = CompositeDisposable()

    private lateinit var viewBinding: FragmentUserListBinding
    private val adapter = UserAdapter()

    private val presenter: UserPresenter by moxyPresenter {
        UserPresenter(GithubRepositoryImpl(), GeekBrainsApp.instance.router)
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
        adapter.setOnUserClickListener(listener)
        with(viewBinding){
            rvGithubUsers.layoutManager = LinearLayoutManager(requireContext())
            rvGithubUsers.adapter = adapter
        }
    }

    private val listener = object : ItemClickListener {
        override fun onUserClick(user: GithubUser) {
            presenter.openUserScreen(user)
        }
    }

    override fun initList(list: List<GithubUser>) {
        adapter.users = list
    }

    override fun showLoading() {
        viewBinding.progressBarUsers.show()
    }

    override fun hideLoading() {
        viewBinding.progressBarUsers.hide()
    }

    override fun showError(message: String?) {
        Log.d("TAG", "message = $message")
    }

    override fun onBackPressed() = presenter.onBackPressed()

    var disposable: Disposable? = null

    private fun rxJava(){
        val observableNames = Observable.just("Egor", "Igor", "Stepan")
        Single.create<String>{
            it.onSuccess("Nu pogodiii")
        }.subscribeByDefault()
            .subscribe(
            {},
            {}
        ).disposeBy(bag)
    }

    private fun Disposable.disposeBy(bag: CompositeDisposable){
        bag.add(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        bag.dispose()
    }

    private fun <T> Single<T>.subscribeByDefault(): Single<T>{
        return this
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}
