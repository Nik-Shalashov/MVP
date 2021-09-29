package ru.android.mvp.models.retrofit

import ru.android.mvp.models.api.ApiFactory
import ru.android.mvp.utils.schedulers.SchedulersFactory

object CloudFactory {
    fun create() : CloudSource = RetrofitGithubUsersRepoImpl(ApiFactory.api, SchedulersFactory.create())
}