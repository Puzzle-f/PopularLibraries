package com.example.popularlibrarieslesson2.presenter

import android.util.Log
import com.example.popularlibrarieslesson2.model.api.GithubUser
import com.example.popularlibrarieslesson2.model.api.IGithubUsersRepo
import com.example.popularlibrarieslesson2.view.IScreens
import com.example.popularlibrarieslesson2.view.UserItemView
import com.example.popularlibrarieslesson2.view.UsersView
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.Disposable
import moxy.MvpPresenter


class UsersPresenter(
    private val uiScheduler: Scheduler,
    private val usersRepo: IGithubUsersRepo,
    private val router: Router,
    private val screens: IScreens
) : MvpPresenter<UsersView>() {

    class UsersListPresenter : IUserListPresenter {
        val users = mutableListOf<GithubUser>()
        override var itemClickListener: ((UserItemView) -> Unit)? = null

        override fun getCount() = users.size

        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            user.login?.let { view.setLogin(it) }
            user.avatarUrl?.let { view.loadAvatar(it) }
        }
    }

    val usersListPresenter = UsersListPresenter()
    private var disposable: Disposable? = null

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        usersListPresenter.itemClickListener = { itemView ->
            val userData = usersListPresenter.users[itemView.pos]
            Log.d("", "Логин пользователя $userData")
            router.navigateTo(screens.userInfo(userData))
        }
    }

    private fun loadData() {
        disposable =
            usersRepo.getUsers()
                .observeOn(uiScheduler)
                .subscribe({ repos ->
                    usersListPresenter.users.clear()
                    usersListPresenter.users.addAll(repos)
                    viewState.updateList()
                }, {
                    println("Error: ${it.message}")
                })
    }

    fun backPressed(): Boolean {
        router.exit()
        disposable?.dispose()
        return true
    }


}
