package ru.android.mvp.models

import io.reactivex.rxjava3.core.Single
import ru.android.mvp.models.api.ServiceApi
import ru.android.mvp.utils.schedulers.Schedulers

class RetrofitGithubUsersRepoImpl(
    private val api: ServiceApi,
    private val schedulers: Schedulers
) : GithubUsersRepo {
    override fun getUsers(): Single<List<GithubUser>> =
        api.getUsers()
            .subscribeOn(schedulers.background())


    override fun getRepos(url : String?): Single<List<GithubRepos>> =
        api.getRepos(url)
            .subscribeOn(schedulers.background())

    override fun getRepo(url: String?): Single<GithubRepos> = api
        .getRepo(url)
        .subscribeOn(schedulers.background())
}