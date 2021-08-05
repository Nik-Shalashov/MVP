package ru.android.mvp

import com.github.terrakok.cicerone.androidx.FragmentScreen

class AndroidScreens: Screens {
    override fun users() = FragmentScreen {UsersFragment.newInstance()
    }
}