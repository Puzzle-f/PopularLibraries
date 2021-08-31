package com.example.popularlibrarieslesson2.model.api.cache

import com.example.popularlibrarieslesson2.model.api.GithubUser
import io.reactivex.rxjava3.core.Single

interface IGithubUsersCache {
    fun getUsers(): Single<List<GithubUser>>
    fun putUsers(users:List<GithubUser>)
}