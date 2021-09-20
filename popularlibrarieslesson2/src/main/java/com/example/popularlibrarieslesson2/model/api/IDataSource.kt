package com.example.popularlibrarieslesson2.model.api

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path


interface IDataSource {
    @GET("/users")
    fun getUsers(): Single<List<GithubUser>>

    @GET("/users/{login}")
    fun fetchUserByLogin(@Path("login") login: String): Single<GithubUser>

    @GET("/users/{login}/repos")
    fun fetchUserRepos(@Path("login") login: String): Single<List<Repository>>


}
