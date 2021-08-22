package ru.android.mvp

import android.os.Bundle
import com.github.terrakok.cicerone.androidx.AppNavigator
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import ru.android.mvp.navigation.App.Navigation.navigatorHolder
import ru.android.mvp.navigation.App.Navigation.router
import ru.android.mvp.databinding.ActivityMainBinding
import ru.android.mvp.navigation.AndroidScreens
import ru.android.mvp.navigation.BackButtonListener
import ru.android.mvp.presenters.MainPresenter
import ru.android.mvp.views.MainView

class MainActivity : MvpAppCompatActivity(), MainView{

    private val navigator = AppNavigator(this, R.id.container)
    private val presenter by moxyPresenter { MainPresenter(router, AndroidScreens) }
    private var vb: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb?.root)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach {
            if(it is BackButtonListener && it.backButtonPressed()){
                return
            }
        }
        presenter.backClicked()
    }
}
