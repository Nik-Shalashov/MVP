package ru.android.mvp.presenters

import ru.android.mvp.views.ItemView
import ru.android.mvp.views.UserItemView

interface ListPresenter<V: ItemView> {
    var itemClickListener: ((V) -> Unit)?
    fun  bindView(view: V)
    fun getCount(): Int
}

interface UserListPresenter: ListPresenter<UserItemView>