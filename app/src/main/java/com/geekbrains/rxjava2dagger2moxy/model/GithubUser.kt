package com.geekbrains.rxjava2dagger2moxy.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GithubUser(val login: String): Parcelable