package ru.android.mvp.utils.schedulers

import io.reactivex.rxjava3.core.Scheduler

interface Schedulers {
    fun main() : Scheduler
    fun background() : Scheduler
}