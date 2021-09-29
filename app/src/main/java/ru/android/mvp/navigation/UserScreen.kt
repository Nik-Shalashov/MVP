package ru.android.mvp.navigation

import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.android.mvp.models.retrofit.GithubUser
import ru.android.mvp.ui.UserFragment

class UserScreen(private val githubUser: GithubUser) {
    fun create(): Screen = FragmentScreen { UserFragment.newInstance(githubUser.login) }
}