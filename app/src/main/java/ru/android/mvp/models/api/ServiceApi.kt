package ru.android.mvp.models.api

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Url
import ru.android.mvp.models.GithubRepos
import ru.android.mvp.models.GithubUser

interface ServiceApi {
    @GET("/users")
    fun getUsers(): Single<List<GithubUser>>
    @GET
    fun getRepos(@Url url : String?) : Single<List<GithubRepos>>
    @GET
    fun getRepo(@Url url : String?) : Single<GithubRepos>
}