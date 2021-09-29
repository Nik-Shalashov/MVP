package ru.android.mvp.navigation

import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.android.mvp.models.retrofit.GithubRepos
import ru.android.mvp.ui.RepoFragment

class RepoScreen(private val repo: GithubRepos) {
    fun create() : Screen = FragmentScreen{ RepoFragment.newInstance(repo.url)}
}