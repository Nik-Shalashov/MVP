package ru.android.mvp.presenters

import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable
import moxy.MvpPresenter
import ru.android.mvp.models.GithubUser
import ru.android.mvp.models.GithubUsersRepo
import ru.android.mvp.views.GhUserView

class GhUserPresenter(private var users: GithubUsersRepo): MvpPresenter<GhUserView>() {

    private var repo: List<GithubUser>? = null
    var disposable: Disposable? = null
    private val repoObserver = object : SingleObserver<List<GithubUser>> {

        override fun onSubscribe(d: Disposable?) {
            disposable = d
        }

        override fun onSuccess(t: List<GithubUser>?) {
            repo = t
        }

        override fun onError(e: Throwable?) {
            disposable?.dispose()
        }
    }

    private fun loadData() {
        users.getUsers().subscribe(repoObserver)
    }

    fun setLogin(pos: Int): GithubUser? {
        loadData()
        return repo?.get(pos)
    }

    override fun onDestroy() {
        disposable?.dispose()
        super.onDestroy()
    }
}