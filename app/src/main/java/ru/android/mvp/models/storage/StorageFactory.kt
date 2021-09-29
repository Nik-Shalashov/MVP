package ru.android.mvp.models.storage

import ru.android.mvp.utils.schedulers.SchedulersFactory

object StorageFactory {
    fun create(): Storage = RoomRepositoryImpl(RoomDB.getInstance() as RoomDB, SchedulersFactory.create())
}