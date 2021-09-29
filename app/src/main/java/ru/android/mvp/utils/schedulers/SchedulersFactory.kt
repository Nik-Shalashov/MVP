package ru.android.mvp.utils.schedulers

object SchedulersFactory {
    fun create(): Schedulers = DefaultSchedulers()
}