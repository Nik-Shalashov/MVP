package ru.android.mvp.presenters

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ru.android.mvp.navigation.UsersScreen
import ru.android.mvp.views.MainView

class MainPresenter(
    private val router: Router
) : MvpPresenter<MainView>() {
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.newRootScreen(UsersScreen.create())
    }

}
