package ru.android.mvp.navigation

import com.github.terrakok.cicerone.Screen

interface Screens {
    fun users(): Screen
    fun ghUser(pos: Int): Screen
}