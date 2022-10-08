package com.geekbrains.rxjava2dagger2moxy.core.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserDbObject(
    @PrimaryKey
    @ColumnInfo(name = PRIMARY_KEY)
    val id: Long,
    val login: String,
    val avatarUrl: String?,
    var reposUrl: String
) {
    companion object {
        const val PRIMARY_KEY = "id"
    }
}
