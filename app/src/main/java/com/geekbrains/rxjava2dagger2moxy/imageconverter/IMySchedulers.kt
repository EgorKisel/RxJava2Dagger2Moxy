package com.geekbrains.rxjava2dagger2moxy.imageconverter

import io.reactivex.rxjava3.core.Scheduler

interface IMySchedulers {

    fun main(): Scheduler

    fun io(): Scheduler

    fun computation(): Scheduler

    fun newThread(): Scheduler

    fun single(): Scheduler

    fun trampoline(): Scheduler

    fun start()

    fun shutdown()
}