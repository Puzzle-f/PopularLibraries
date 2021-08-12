package com.example.popularlibrarieslesson2.view

import com.example.popularlibrarieslesson2.model.api.GithubUser
import com.example.popularlibrarieslesson2.model.api.Repository
import com.github.terrakok.cicerone.Screen

interface IScreens {
    fun users(): Screen
    fun userInfo(userData: GithubUser): Screen
    fun repoInfo(): Screen
}