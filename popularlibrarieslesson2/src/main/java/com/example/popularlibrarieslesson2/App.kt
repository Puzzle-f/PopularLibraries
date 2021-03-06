package com.example.popularlibrarieslesson2

import android.app.Application
import com.example.popularlibrarieslesson2.model.api.RoomData.db.Database
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router


class App: Application() {
    companion object {
        lateinit var instance: App
    }

    private val cicerone: Cicerone<Router> by lazy {
        Cicerone.create()
    }
    val navigatorHolder get() = cicerone.getNavigatorHolder()
    val router get() = cicerone.router

    override fun onCreate() {
        super.onCreate()
        Database.create(this)
        instance = this
    }
}