package ru.android.mvp.views

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType
import ru.android.mvp.models.retrofit.GithubUser

@StateStrategyType(AddToEndSingleStrategy::class)
interface UserView : MvpView {
    fun showUser(githubUser: GithubUser)
    fun showAvatar(githubUser: GithubUser)
    fun showError(error: Throwable)
    fun updateRepose()
    fun init()
}