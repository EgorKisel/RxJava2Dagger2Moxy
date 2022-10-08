package com.geekbrains.rxjava2dagger2moxy.core.database

import androidx.room.*
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
abstract class UserDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(userDbObject: UserDbObject): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertAll(userDbObject: List<UserDbObject>): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertAllRepos(repoDBObject: List<RepoDBObject>): Completable

    @Query("SELECT * FROM users")
    abstract fun queryForAllUsers(): Single<List<UserDbObject>>

    @Query("Select * from repos")
    abstract fun queryForAllRepos(): Single<List<RepoDBObject>>

    @Delete
    abstract fun delete(userDbObject: UserDbObject): Completable

    @Transaction
    @Query("SELECT * FROM users WHERE login = :login")
    abstract fun getUserWithRepos(login: String): Single<UserWithReposDBObject>
}