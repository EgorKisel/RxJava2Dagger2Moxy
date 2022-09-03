package com.geekbrains.rxjava2dagger2moxy.imageconverter

object MySchedulersFactory {
    fun create(): IMySchedulers = MySchedulers()
}