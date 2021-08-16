package ru.android.mvp.presenters

import moxy.MvpPresenter
import ru.android.mvp.models.GithubUsersRepo
import ru.android.mvp.views.GhUserView

class GhUserPresenter(
    private val repo: GithubUsersRepo,
    private val userLogin: String,
): MvpPresenter<GhUserView>() {

    override fun onFirstViewAttach() {
        repo
            .getUserByLogin(userLogin)
            ?.let(viewState::showUser)
    }
}