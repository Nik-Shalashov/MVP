package ru.android.mvp.models.api

import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import ru.android.mvp.BuildConfig

object GithubInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        request.newBuilder()
            .header("Authorization", BuildConfig.GITHUB_DB_APIKEY)
            .build().apply {
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            }
        return chain.proceed(request)
    }
}