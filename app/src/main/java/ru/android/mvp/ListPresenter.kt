package ru.android.mvp

interface ListPresenter<V: ItemView> {
    var itemClickListener: ((V) -> Unit)?
    fun  bindView(view: V)
    fun getCount(): Int
}

interface UserListPresenter: ListPresenter<UserItemView>