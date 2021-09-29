package ru.android.mvp

import android.app.Application
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import ru.android.mvp.models.storage.RoomDB

class App : Application() {
    companion object Navigation {
        /** Before when we'll used Dagger lib by DI**/
        private val cicerone : Cicerone<Router> by lazy {
            Cicerone.create()
        }
        val navigatorHolder = cicerone.getNavigatorHolder()
        val router = cicerone.router
    }

    override fun onCreate() {
        super.onCreate()
        RoomDB.create(this)
    }
}