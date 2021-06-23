package com.example.popularlibrarieslesson2.presenter

import android.util.Log
import com.example.popularlibrarieslesson2.R
import com.example.popularlibrarieslesson2.model.GithubUser
import com.example.popularlibrarieslesson2.model.GithubUsersRepo
import com.example.popularlibrarieslesson2.view.IScreens
import com.example.popularlibrarieslesson2.view.UsersView
import com.example.popularlibrarieslesson2.view.UserItemView
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.Screen
import moxy.MvpPresenter


class UsersPresenter(val usersRepo: GithubUsersRepo, val router: Router, private val screens: IScreens) : MvpPresenter<UsersView>() {
    class UsersListPresenter : IUserListPresenter {
        val users = mutableListOf<GithubUser>()
        override var itemClickListener: ((UserItemView) -> Unit)? = null

        override fun getCount() = users.size

        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            user.login?.let { view.setLogin(it) }
        }
    }

    val usersListPresenter = UsersListPresenter()

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

    fun loadData() {
        val users = usersRepo.getUsers()
        usersListPresenter.users.addAll(users)
        viewState.updateList()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

}
