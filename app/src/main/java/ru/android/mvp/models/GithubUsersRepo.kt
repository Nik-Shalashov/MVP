package ru.android.mvp.models

import io.reactivex.rxjava3.core.Single

interface GithubUsersRepo {
    fun getUsers() : Single<List<GithubUser>>
    fun getRepos(url: String?): Single<List<GithubRepos>>
    fun getRepo(url: String?): Single<GithubRepos>
}