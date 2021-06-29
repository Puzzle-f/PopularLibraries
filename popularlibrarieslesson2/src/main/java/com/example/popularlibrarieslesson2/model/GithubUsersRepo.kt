package com.example.popularlibrarieslesson2.model

import io.reactivex.rxjava3.core.Observable


class GithubUsersRepo {

    fun repositoriesObservable(): Observable<GithubUser> {
        return Observable.fromIterable(listOf(
            GithubUser("login1"),
            GithubUser("login2"),
            GithubUser("login3"),
            GithubUser("login4"),
            GithubUser("login5")
        ))
    }

}
