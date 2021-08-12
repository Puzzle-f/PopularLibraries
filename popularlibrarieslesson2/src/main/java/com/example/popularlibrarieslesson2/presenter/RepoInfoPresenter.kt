package com.example.popularlibrarieslesson2.presenter

import com.example.popularlibrarieslesson2.model.api.IGithubUsersRepo
import com.example.popularlibrarieslesson2.view.IScreens
import com.example.popularlibrarieslesson2.view.RepoView
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.Disposable
import moxy.MvpPresenter

class RepoInfoPresenter(
    private val name: String,
    private val uiScheduler: Scheduler,
    private val usersRepo: IGithubUsersRepo,
    private val router: Router,
    private val screens: IScreens
    ): MvpPresenter<RepoView>()  {


private var disposable: Disposable? = null

    private fun loadData(){

    }






}