package ru.android.mvp.views

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType
import ru.android.mvp.models.GithubUser

@StateStrategyType(AddToEndSingleStrategy::class)
interface GhUserView: MvpView {
    fun showUser(user: GithubUser)
}