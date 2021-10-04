package ru.android.mvp.models.storage

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [RoomGithubUser::class, RoomGithubRepo::class],
    version = 1,
    exportSchema = false
)
abstract class RoomDB : RoomDatabase() {
    abstract val userDao: UserDAO
    abstract val repoDao: RepoDAO
}