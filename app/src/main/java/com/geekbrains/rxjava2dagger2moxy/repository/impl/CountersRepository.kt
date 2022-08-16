package com.geekbrains.rxjava2dagger2moxy.repository.impl

class CountersRepository {
    private val counters = mutableListOf(0, 0, 0)

    fun getCurrent(position: Int): Int {
        return counters[position]
    }

    fun next(position: Int): Int {
        return counters[position]++
    }

    fun set(position: Int, value: Int) {
        counters[position] = value
    }

    fun getCounters() = counters
}