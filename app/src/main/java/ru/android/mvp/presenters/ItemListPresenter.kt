package ru.android.mvp.presenters

import ru.android.mvp.views.ItemView
import ru.android.mvp.views.UserItemView

interface ItemListPresenter<View : ItemView> {
    var itemClickedListener: ((View) -> Unit)?
    fun bindView(view: View)
    fun getCount(): Int
}

interface UserItemListPresenter : ItemListPresenter<UserItemView>