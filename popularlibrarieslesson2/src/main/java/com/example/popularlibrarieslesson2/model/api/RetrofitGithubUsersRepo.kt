package com.example.popularlibrarieslesson2.model.api

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubUsersRepo(val api: IDataSource) : IGithubUsersRepo {
    override fun getUsers() = api.getUsers().subscribeOn(Schedulers.io())

    override fun fetchUserByLogin(login: String): Single<GithubUser> {
        return api.fetchUserByLogin(login).subscribeOn(Schedulers.io())
    }

    override fun fetchUserRepo(login: String): Single<List<Repository>> {
        return api.fetchUserRepos(login).subscribeOn(Schedulers.io())
    }
}
