package ru.android.mvp.presenters

import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import moxy.MvpPresenter
import ru.android.mvp.models.GithubUsersRepo
import ru.android.mvp.utils.schedulers.Schedulers
import ru.android.mvp.views.RepoView
import javax.inject.Inject

class RepoPresenter @Inject constructor(
    private val repo: GithubUsersRepo,
    private val repoUrl: String?,
    private val schedulers: Schedulers
) : MvpPresenter<RepoView>() {
    private val disposable = CompositeDisposable()
    override fun onFirstViewAttach() {
        disposable += repo
            .getRepo(repoUrl)
            .observeOn(schedulers.main())
            .subscribe(
                {
                    viewState.showRepoName(it)
                    viewState.showRepoForks(it)
                    viewState.showRepoDate(it)
                },
                viewState::showError
            )
    }

    override fun onDestroy() {
        disposable.clear()
    }
}