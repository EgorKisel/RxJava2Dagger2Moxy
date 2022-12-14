package com.geekbrains.rxjava2dagger2moxy.core.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "repos")
data class RepoDBObject (
    @PrimaryKey
    @ColumnInfo(name = PRIMARY_KEY)
    val id: Long,
    val forks: Int?=null,
    val name: String?=null,
    val nodeId: String?=null,
    val description: String?=null,
    var createdAt: String?=null,
    val updatedAt: String?=null,
    val language: String?=null,
    @ColumnInfo(name = FOREIGN_USER_KEY)
    val userId: Long
) {
    companion object{
        const val PRIMARY_KEY = "id"
        const val FOREIGN_USER_KEY = "userId"
    }
}