package ru.android.mvp.presenters

import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.SingleObserver
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
        var users = mutableListOf<GithubUser>()
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

    private val repoObserver = object : SingleObserver<List<GithubUser>> {

        var disposable: Disposable? = null

        override fun onSubscribe(d: Disposable?) {
            disposable = d
        }

        override fun onSuccess(t: List<GithubUser>?) {
            usersListPresenter.users = t as MutableList<GithubUser>
            viewState.updateList()
        }

        override fun onError(e: Throwable?) {
            disposable?.dispose()
        }
    }

    private fun loadData() {
        usersRepo.getUsers().subscribe(repoObserver)
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}
