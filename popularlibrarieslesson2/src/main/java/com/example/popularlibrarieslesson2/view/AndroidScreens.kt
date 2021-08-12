package com.example.popularlibrarieslesson2.view

import com.example.popularlibrarieslesson2.model.api.GithubUser
import com.example.popularlibrarieslesson2.view.listRepositoriesView.RepositoriesFragment
import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen

class AndroidScreens : IScreens {
    // просмотр списка юзеров
    override fun users() = FragmentScreen { UsersFragment.newInstance() }

    // просмотр списка репозитроиев выбранного юзера
    override fun userInfo(userData: GithubUser) =
        FragmentScreen { RepositoriesFragment.newInstance(userData.login.toString()) }

    // просмотр информации о выбранном репозитории
    override fun repoInfo(): Screen {
        TODO("Not yet implemented")
    }

    fun repoInfo1(userData: GithubUser): Screen {
        return FragmentScreen { RepositoriesFragment.newInstance(userData.login.toString()) }
    }

}
