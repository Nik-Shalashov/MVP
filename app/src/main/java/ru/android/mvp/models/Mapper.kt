package ru.android.mvp.models

import ru.android.mvp.models.retrofit.GithubUser

object Mapper {
    fun filter(users: List<GithubUser>, login: String) = users.find { user -> user.login == login }
}