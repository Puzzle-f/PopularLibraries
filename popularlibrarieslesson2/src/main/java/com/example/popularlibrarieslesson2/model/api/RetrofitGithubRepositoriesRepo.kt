package com.example.popularlibrarieslesson2.model.api

import com.example.popularlibrarieslesson2.model.api.RoomData.IGithubRepositoriesRepo
import com.example.popularlibrarieslesson2.model.api.RoomData.RoomGithubRepository
import com.example.popularlibrarieslesson2.model.api.RoomData.db.Database
import com.example.popularlibrarieslesson2.view.network.INetworkStatus
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubRepositoriesRepo(
    val api: IDataSource,
    val networkStatus: INetworkStatus,
    val db: Database
) : IGithubUsersRepo {
    private fun getRepositories(login: String) =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                login?.let { url ->
                    api.fetchUserRepos(url)
                        .flatMap { repositories ->
                            Single.fromCallable {
                                val roomUser = login?.let { db.userDao.findByLogin(it) }
                                    ?: throw RuntimeException("No such user in cache")
                                val roomRepos = repositories.map {
                                    RoomGithubRepository(
                                        it.name ?: "",
                                        roomUser.id ?: ""
                                    )
                                }
                                db.repositoryDao.insert(roomRepos)
                                repositories
                            }
                        }
                } ?: Single.error<List<Repository>>(RuntimeException("User has no repos url"))
                    .subscribeOn(Schedulers.io())
            } else {
                Single.fromCallable {
                    val roomUser = login?.let { db.userDao.findByLogin(it) }
                        ?: throw RuntimeException("No such user in cache")
                    db.repositoryDao.findForUser(roomUser.id).map { Repository(it.name) }
                }

            }
        }.subscribeOn(Schedulers.io())

    override fun getUsers(): Single<List<GithubUser>> {
        TODO("Not yet implemented")
    }

    override fun fetchUserByLogin(login: String): Single<GithubUser> {
        TODO("Not yet implemented")
    }

    override fun fetchUserRepo(login: String): Single<List<Repository>> {
        return getRepositories(login)
    }
}
