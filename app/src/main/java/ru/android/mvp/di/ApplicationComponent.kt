package ru.android.mvp.di

import android.content.Context
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import ru.android.mvp.MVPApp
import ru.android.mvp.di.modules.GithubApiModule
import ru.android.mvp.di.modules.GithubModule
import ru.android.mvp.di.modules.StorageModule
import ru.android.mvp.utils.ImageLoader
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidInjectionModule::class, GithubApiModule::class, GithubModule::class,
        StorageModule::class]
)
interface ApplicationComponent : AndroidInjector<MVPApp> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun withContext(context: Context): Builder

        @BindsInstance
        fun withRouter(router: Router): Builder

        @BindsInstance
        fun withNavigationHolder(navigatorHolder: NavigatorHolder): Builder

        @BindsInstance
        fun withImageLoader(imageLoader: ImageLoader): Builder
        fun build(): ApplicationComponent
    }
}