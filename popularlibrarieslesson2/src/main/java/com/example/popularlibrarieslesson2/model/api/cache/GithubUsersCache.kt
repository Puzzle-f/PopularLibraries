package com.example.popularlibrarieslesson2.model.api.cache

import com.example.popularlibrarieslesson2.model.api.GithubUser
import com.example.popularlibrarieslesson2.model.api.RoomData.RoomGithubUser
import com.example.popularlibrarieslesson2.model.api.RoomData.db.Database
import io.reactivex.rxjava3.core.Single

class GithubUsersCache(val db: Database) : IGithubUsersCache {

    override fun getUsers(): Single<List<GithubUser>> {
        return Single.fromCallable {
            db.userDao.getAll().map { roomUser ->
                GithubUser(roomUser.id, roomUser.login, roomUser.avatarUrl)
            }
        }
    }

    override fun putUsers(users: List<GithubUser>) {
        Single.fromCallable {
            val roomUsers = users.map { user ->
                RoomGithubUser(
                    user.id ?: "",
                    user.login ?: "",
                    user.avatarUrl ?: ""
                )
            }
            db.userDao.insert(roomUsers)
            users
        }
    }


}


