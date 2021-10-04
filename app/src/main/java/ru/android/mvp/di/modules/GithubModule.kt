package ru.android.mvp.di.modules

import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.android.mvp.ui.MainActivity
import ru.android.mvp.models.GithubUsersRepo
import ru.android.mvp.models.RepositoryImpl
import ru.android.mvp.models.network.AndroidNetworkStatus
import ru.android.mvp.models.network.NetworkStatus
import ru.android.mvp.models.retrofit.CloudSource
import ru.android.mvp.models.retrofit.RetrofitGithubUsersRepoImpl
import ru.android.mvp.models.storage.DataSource
import ru.android.mvp.models.storage.RoomRepositoryImpl
import ru.android.mvp.ui.RepoFragment
import ru.android.mvp.ui.UserFragment
import ru.android.mvp.ui.UsersListFragment
import ru.android.mvp.utils.schedulers.DefaultSchedulers
import ru.android.mvp.utils.schedulers.Schedulers

@Module
interface GithubModule {
    @ContributesAndroidInjector
    fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector
    fun bindUsersFragment(): UsersListFragment

    @ContributesAndroidInjector
    fun bindUserFragment(): UserFragment

    @ContributesAndroidInjector
    fun bindRepoFragment(): RepoFragment


    @Binds
    fun bindRepository(repository: RepositoryImpl): GithubUsersRepo

    @Binds
    fun bindCloudStorage(cloud: RetrofitGithubUsersRepoImpl): CloudSource

    @Binds
    fun bindCacheStorage(storage: RoomRepositoryImpl): DataSource

    @Binds
    fun bindNetworkStatus(network: AndroidNetworkStatus): NetworkStatus

    @Binds
    fun bindScheduler(schedulers: DefaultSchedulers): Schedulers


}