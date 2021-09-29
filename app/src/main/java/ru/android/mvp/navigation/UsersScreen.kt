package ru.android.mvp.navigation

import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.android.mvp.ui.UsersListFragment

object UsersScreen {
    fun create() = FragmentScreen { UsersListFragment.newInstance() }
}