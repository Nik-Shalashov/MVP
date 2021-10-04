package ru.android.mvp.models

import io.reactivex.rxjava3.core.Single
import ru.android.mvp.models.network.NetworkStatus
import ru.android.mvp.models.retrofit.CloudSource
import ru.android.mvp.models.retrofit.GithubRepos
import ru.android.mvp.models.retrofit.GithubUser
import ru.android.mvp.models.storage.DataSource
import ru.android.mvp.utils.schedulers.Schedulers
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val cloud: CloudSource,
    private val dataSource: DataSource,
    private val network: NetworkStatus,
    private val schedulers: Schedulers
) : GithubUsersRepo {
    override fun getUsers(): Single<List<GithubUser>> = network
        .onLineSingle()
        .flatMap { isOnline ->
            if (isOnline) {
                cloud.getUsers().map { users ->
                    dataSource.insertUsers(users)
                    users
                }
            } else {
                dataSource.getUsers()
            }
        }.subscribeOn(schedulers.background())

    override fun getRepos(url: String?): Single<List<GithubRepos>> = network
        .onLineSingle()
        .flatMap { isOnline ->
            if (isOnline) {
                cloud.getRepos(url).map { repos ->
                    dataSource.insertGithubRepos(repos, url)
                    repos
                }
            } else {
                dataSource.getRepos(url)
            }
        }.subscribeOn(schedulers.background())

    override fun getRepo(url: String?): Single<GithubRepos> = network
        .onLineSingle()
        .flatMap { isOnline ->
            if (isOnline) {
                cloud.getRepo(url)
            } else {
                dataSource.getRepo(url)
            }
        }.subscribeOn(schedulers.background())
}