package ru.android.mvp.di.modules

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.android.mvp.models.storage.RoomDB

@Module
class StorageModule {
    @Provides
    fun provideGithubStorageDatabase(context: Context): RoomDB =
        Room.databaseBuilder(context, RoomDB::class.java, "github.db").build()
}