package ru.android.mvp.presenters

import moxy.MvpPresenter
import ru.android.mvp.models.GithubUsersRepo
import ru.android.mvp.views.GhUserView

class GhUserPresenter(private val user: GithubUsersRepo): MvpPresenter<GhUserView>() {
    fun setLogin(pos: Int) = user.getUsers()[pos]
}