package ru.android.mvp.models

object Mapper {
    fun filter(users: List<GithubUser>, login: String) = users.find { user -> user.login == login }
}