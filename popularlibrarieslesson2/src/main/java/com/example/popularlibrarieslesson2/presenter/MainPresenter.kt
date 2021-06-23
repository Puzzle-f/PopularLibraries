package com.example.popularlibrarieslesson2.presenter

import com.example.popularlibrarieslesson2.view.IScreens
import com.example.popularlibrarieslesson2.view.MainView
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter


class MainPresenter(private val router: Router, private val screens: IScreens) : MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(screens.users())
    }

    fun backClicked() {
        router.exit()
    }
}

