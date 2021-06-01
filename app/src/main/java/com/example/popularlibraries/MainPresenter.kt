package com.example.popularlibraries

import android.util.Log
import moxy.MvpPresenter

class MainPresenter(val model: CountersModel): MvpPresenter<MainView>() {

    fun button1Click(){
        val count = model.next(0)
        viewState.setButton1Text(count.toString())
        Log.d("TAG", "count1 = $count")
    }

    fun button2Click(){
        val count = model.next(1)
        viewState.setButton2Text(count.toString())
        Log.d("TAG", "count2 = $count")
    }

    fun button3Click(){
        val count = model.next(2)
        viewState.setButton3Text(count.toString())
        Log.d("TAG", "count3 = $count")
    }
}
