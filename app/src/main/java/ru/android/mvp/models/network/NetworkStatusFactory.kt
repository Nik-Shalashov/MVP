package ru.android.mvp.models.network

import android.content.Context

object NetworkStatusFactory {
    fun create(context: Context?) : NetworkStatus = AndroidNetworkStatus(context)
}