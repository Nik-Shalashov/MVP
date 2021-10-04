package ru.android.mvp.ui

import android.os.Bundle
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import moxy.ktx.moxyPresenter
import ru.android.mvp.R
import ru.android.mvp.databinding.ActivityMainBinding
import ru.android.mvp.presenters.MainPresenter
import ru.android.mvp.ui.abs.AbsActivity
import ru.android.mvp.views.MainView
import javax.inject.Inject

/**For creating an instance of moxyPresenter need to MainActivity instanced of MvpAppCompatActivity()**/
class MainActivity : AbsActivity(R.layout.activity_main), MainView {
    private val binding: ActivityMainBinding by viewBinding(CreateMethod.INFLATE)
    private val navigator = AppNavigator(this, R.id.container)

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    lateinit var router: Router
    private val presenter by moxyPresenter { MainPresenter(router) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.root
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

