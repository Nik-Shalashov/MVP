package ru.android.mvp.models.storage

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [RoomGithubUser::class, RoomGithubRepo::class],
    version = 1,
    exportSchema = false
)
abstract class RoomDB : RoomDatabase() {
    abstract val userDao: UserDAO
    abstract val repoDao: RepoDAO

    companion object {
        private const val DB_NAME = "github.db"
        private var instance: RoomDatabase? = null
        fun getInstance() =
            instance ?: throw RuntimeException("Database has no created, please create")

        fun create(context: Context) {
            if (instance == null) {
                instance = Room.databaseBuilder(context, RoomDB::class.java, DB_NAME).build()
            }
        }
    }
}