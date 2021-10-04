package ru.android.mvp.models.storage

import io.reactivex.rxjava3.core.Single
import ru.android.mvp.models.retrofit.GithubRepos
import ru.android.mvp.models.retrofit.GithubUser

interface DataSource  {
    fun insertUsers(users: List<GithubUser>)
    fun insertGithubRepos(repos: List<GithubRepos>, usersUrl: String?)
    fun getUsers(): Single<List<GithubUser>>
    fun getRepos(url : String?): Single<List<GithubRepos>>
    fun getRepo(url : String?): Single<GithubRepos>
}