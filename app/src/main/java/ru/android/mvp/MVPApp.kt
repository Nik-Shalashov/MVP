package ru.android.mvp

import com.github.terrakok.cicerone.Cicerone
import ru.android.mvp.di.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import ru.android.mvp.utils.ImageLoader

class MVPApp : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<MVPApp> =
        DaggerApplicationComponent
            .builder()
            .withContext(applicationContext)
            .withImageLoader(ImageLoader)
            .apply {
                val cicerone = Cicerone.create()
                withRouter(cicerone.router)
                withNavigationHolder(cicerone.getNavigatorHolder())
            }
            .build()


}