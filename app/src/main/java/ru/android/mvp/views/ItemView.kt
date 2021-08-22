package ru.android.mvp.views

import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

interface ItemView {
    var pos: Int
}

@StateStrategyType(AddToEndSingleStrategy::class)
interface UserItemView: ItemView {
    fun setLogin(text: String)
}