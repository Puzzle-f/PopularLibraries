package com.example.popularlibrarieslesson2.model.api

import com.example.popularlibrarieslesson2.model.api.RoomData.RoomGithubUser
import com.example.popularlibrarieslesson2.model.api.RoomData.db.Database
import com.example.popularlibrarieslesson2.model.api.cache.GithubUsersCache
import com.example.popularlibrarieslesson2.model.api.cache.IGithubUsersCache
import com.example.popularlibrarieslesson2.view.network.INetworkStatus
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubUsersRepo(val api: IDataSource, val networkStatus: INetworkStatus, val db: Database) : IGithubUsersRepo {
    private val userCache: IGithubUsersCache
        get() {
            TODO()
        }

    override fun getUsers() = networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
            api.getUsers()
                .flatMap { users ->

                    Single.fromCallable {
                        GithubUsersCache(db).putUsers(users)
                        users
                    }
                }
        } else {
      GithubUsersCache(db).getUsers()
        }
    }.subscribeOn(Schedulers.io())


    override fun fetchUserByLogin(login: String): Single<GithubUser> {
        return api.fetchUserByLogin(login).subscribeOn(Schedulers.io())
    }

    override fun fetchUserRepo(login: String): Single<List<Repository>> {
        return api.fetchUserRepos(login).subscribeOn(Schedulers.io())
    }
}
