package ru.android.mvp.presenters

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ru.android.mvp.navigation.Screens
import ru.android.mvp.views.MainView

class MainPresenter(private val router: Router, private val screens: Screens) : MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(screens.users())
    }

    fun backClicked() {
        router.exit()
    }
}
