package com.geekbrains.rxjava2dagger2moxy.core.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [UserDbObject::class, RepoDBObject::class],
    version = 1
)
abstract class GithubAppDb : RoomDatabase() {

    abstract fun userDao(): UserDAO

    companion object {
        fun create(context: Context): GithubAppDb {
            return Room.databaseBuilder(
                context, GithubAppDb::class.java, "github.db"
            ).build()
        }
    }
}