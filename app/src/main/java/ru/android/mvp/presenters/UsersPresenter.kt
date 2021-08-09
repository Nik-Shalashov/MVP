package ru.android.mvp.presenters

import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import moxy.MvpPresenter
import ru.android.mvp.models.GithubUser
import ru.android.mvp.models.GithubUsersRepo
import ru.android.mvp.navigation.AndroidScreens
import ru.android.mvp.views.UserItemView
import ru.android.mvp.views.UsersView

class UsersPresenter(private val usersRepo: GithubUsersRepo, private val router: Router, private val screens: AndroidScreens) :
    MvpPresenter<UsersView>() {

    class UsersListPresenter : UserListPresenter {
        val users = mutableListOf<GithubUser>()
        override var itemClickListener: ((UserItemView) -> Unit)? = null

        override fun getCount() = users.size
        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            view.setLogin(user.login)
        }
    }

    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
        usersListPresenter.itemClickListener = { itemView ->
            router.navigateTo(screens.ghUser(itemView.pos))
        }
    }

    private val repoObserver = object : Observer<GithubUser> {
        var disposable: Disposable? = null

        override fun onSubscribe(d: Disposable?) {
            disposable = d
        }

        override fun onNext(t: GithubUser?) {
            t?.let { usersListPresenter.users.add(it) }
        }

        override fun onError(e: Throwable?) {
            println("onError: ${e?.message}")
        }

        override fun onComplete() {
            viewState.updateList()
        }
    }

    private fun loadData() {
        usersRepo.fromIterable().subscribe(repoObserver)
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}
