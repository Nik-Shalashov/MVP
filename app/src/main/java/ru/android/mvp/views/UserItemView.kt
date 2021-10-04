package ru.android.mvp.views

import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface UserItemView : ItemView {
    fun setLogin(login: String)
    fun setAvatar(url : String)
}