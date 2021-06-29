package com.example.popularlibrarieslesson2.view

import com.example.popularlibrarieslesson2.fragment.UsersFragment
import com.example.popularlibrarieslesson2.model.GithubUser
import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen

class AndroidScreens : IScreens {
    override fun users() = FragmentScreen { UsersFragment.newInstance() }
    override fun userInfo(userData: GithubUser): Screen {
    return FragmentScreen { UserInfoFragment.newInstance(userData) }
    }
}
