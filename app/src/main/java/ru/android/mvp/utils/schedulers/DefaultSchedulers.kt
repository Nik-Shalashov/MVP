package ru.android.mvp.utils.schedulers

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers.io

class DefaultSchedulers : Schedulers {
    override fun main(): Scheduler  = AndroidSchedulers.mainThread()

    override fun background(): Scheduler = io()
}