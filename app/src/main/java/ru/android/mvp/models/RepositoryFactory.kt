package ru.android.mvp.models

import ru.android.mvp.models.api.ApiFactory
import ru.android.mvp.utils.schedulers.SchedulersFactory


object RepositoryFactory {
    fun create(): GithubUsersRepo = RetrofitGithubUsersRepoImpl(ApiFactory.api, SchedulersFactory.create())
}