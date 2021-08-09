package ru.android.mvp.navigation

import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.android.mvp.GhUserFragment
import ru.android.mvp.UsersFragment

object AndroidScreens: Screens {
    override fun users(): Screen = FragmentScreen {
        UsersFragment.newInstance()
    }

    override fun ghUser(pos: Int): Screen = FragmentScreen {
        GhUserFragment.newInstance(pos)
    }
}