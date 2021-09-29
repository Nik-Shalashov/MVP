package ru.android.mvp.models

import ru.android.mvp.models.network.NetworkStatus
import ru.android.mvp.models.retrofit.CloudFactory
import ru.android.mvp.models.storage.StorageFactory
import ru.android.mvp.utils.schedulers.SchedulersFactory

object RepositoryFactory {
    fun create(networkStatus: NetworkStatus): GithubUsersRepo =
        RepositoryImpl(
            CloudFactory.create(),
            StorageFactory.create(),
            networkStatus,
            SchedulersFactory.create()
        )
}