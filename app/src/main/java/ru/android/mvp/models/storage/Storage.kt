package ru.android.mvp.models.storage

import ru.android.mvp.models.GithubUsersRepo
import ru.android.mvp.models.retrofit.GithubRepos
import ru.android.mvp.models.retrofit.GithubUser

interface Storage : GithubUsersRepo {
    fun insertUsers(users: List<GithubUser>)
    fun insertGithubRepos(repos: List<GithubRepos>, usersUrl: String?)
}