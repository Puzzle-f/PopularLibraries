package com.example.popularlibrarieslesson2.model.api

import io.reactivex.rxjava3.core.Single

interface IGithubUsersRepo {
    fun getUsers(): Single<List<GithubUser>>

    fun fetchUserByLogin(login: String): Single<GithubUser>

    fun fetchUserRepo(login: String): Single<List<GithubUser>>

}
