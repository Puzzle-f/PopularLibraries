package com.example.popularlibrarieslesson2.model.api.RoomData

import com.example.popularlibrarieslesson2.model.api.GithubUser
import com.example.popularlibrarieslesson2.model.api.Repository
import io.reactivex.rxjava3.core.Single

interface IGithubRepositoriesRepo {
    fun getRepositories(user: GithubUser): Single<List<Repository>>
}