package ru.android.mvp.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GithubRepos (
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("created_at")
    val date: String?,
    @SerializedName("language")
    val language: String?,
    @SerializedName("forks_count")
    val forks: Int?,
    @SerializedName("url")
    val url: String?
) : Parcelable