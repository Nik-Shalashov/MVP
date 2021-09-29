package ru.android.mvp

import android.os.Bundle
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.terrakok.cicerone.androidx.AppNavigator
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import ru.android.mvp.App.Navigation.navigatorHolder
import ru.android.mvp.App.Navigation.router
import ru.android.mvp.databinding.ActivityMainBinding
import ru.android.mvp.presenters.MainPresenter
import ru.android.mvp.views.MainView

/**For creating an instance of moxyPresenter need to MainActivity instanced of MvpAppCompatActivity()**/
class MainActivity : MvpAppCompatActivity(), MainView {
    private val binding : ActivityMainBinding by viewBinding(CreateMethod.INFLATE)
    private val navigator = AppNavigator(this, R.id.container)
    private val mainPresenter by moxyPresenter { MainPresenter(router) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

}

