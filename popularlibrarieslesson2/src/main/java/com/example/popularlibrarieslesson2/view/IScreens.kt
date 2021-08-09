package com.example.popularlibrarieslesson2.view

import com.example.popularlibrarieslesson2.model.api.GithubUser
import com.github.terrakok.cicerone.Screen

interface IScreens {
    fun users(): Screen
    fun userInfo(userData: GithubUser): Screen
}