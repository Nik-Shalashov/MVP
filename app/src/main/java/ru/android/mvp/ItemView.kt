package ru.android.mvp

interface ItemView {
    var pos: Int
}

interface UserItemView: ItemView {
    fun setLogin(text: String)
}