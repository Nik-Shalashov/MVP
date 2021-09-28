package ru.android.mvp.views

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType
import ru.android.mvp.models.GithubRepos

@StateStrategyType(AddToEndSingleStrategy::class)
interface RepoView : MvpView {
    fun showRepoName(repo: GithubRepos)
    fun showRepoForks(repo: GithubRepos)
    fun showRepoDate(repo: GithubRepos)
    fun showRepoLanguage(repo: GithubRepos)
    fun showError(error: Throwable)
}