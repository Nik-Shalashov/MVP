package ru.android.mvp.models.api


import ru.android.mvp.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

object GithubInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        request.newBuilder()
            .header("Authorization", BuildConfig.GITHUB_DB_APIKEY)
            .build()
        return chain.proceed(request)
    }
}