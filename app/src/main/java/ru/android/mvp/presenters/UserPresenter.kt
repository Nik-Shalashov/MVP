package ru.android.mvp.presenters

import android.util.Log
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import moxy.MvpPresenter
import ru.android.mvp.models.retrofit.GithubRepos
import ru.android.mvp.models.retrofit.GithubUser
import ru.android.mvp.models.GithubUsersRepo
import ru.android.mvp.models.Mapper
import ru.android.mvp.navigation.RepoScreen
import ru.android.mvp.utils.schedulers.Schedulers
import ru.android.mvp.views.RepoItemView
import ru.android.mvp.views.UserView

class UserPresenter(
    private val userLogin: String,
    private val gitHubRepo: GithubUsersRepo,
    private val schedulers: Schedulers,
    private val router: Router
) : MvpPresenter<UserView>() {
    private val repos = mutableListOf<GithubRepos>()
    private var disposable = CompositeDisposable()
    val reposPresenter = ReposPresenter()
    override fun onFirstViewAttach() {
        viewState.init()
        disposable += gitHubRepo
            .getUsers()
            .map { Mapper.filter(it, userLogin) }
            .observeOn(schedulers.main())
            .subscribe(
                {
                    it?.let { user ->
                        viewState.showUser(user)
                        viewState.showAvatar(user)
                        loadReposData(user)
                    }
                },
                viewState::showError
            )
    }

    private fun loadReposData(user: GithubUser) {
        disposable += gitHubRepo
            .getRepos(user.reposUrl)
            .observeOn(schedulers.main())
            .subscribe(
                {
                    repos.addAll(it)
                    reposPresenter.repos.addAll(repos)
                    viewState.updateRepose()

                },
                viewState::showError
            )
        reposPresenter.itemClickedListener = {view ->
            router.navigateTo(RepoScreen(repos[view.pos]).create())
        }
    }

    class ReposPresenter() : ItemListPresenter<RepoItemView>{
        val repos = mutableListOf<GithubRepos>()

        override fun bindView(view: RepoItemView) {
            val repo = repos[view.pos]
            view.setRepoName(repo.name)
            view.setLanguage(repo.language)
            view.setDate(repo.date)
            Log.e("repos", repo.toString())
        }
        override fun getCount(): Int = repos.size
        override var itemClickedListener: ((RepoItemView) -> Unit)? = null

    }

    override fun onDestroy() {
        disposable.clear()
        super.onDestroy()
    }
}