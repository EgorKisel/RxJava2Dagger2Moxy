package com.geekbrains.rxjava2dagger2moxy.core.network

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserDto(
    @Expose
    @SerializedName("id") val id: Long,
    @Expose
    @SerializedName("login") val login: String,
    @Expose
    @SerializedName("avatar_url") val avatarUrl: String,
    @Expose
    @SerializedName("repos_url") val reposUrl: String
) : Parcelable