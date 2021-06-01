package com.example.popularlibraries

import android.util.Log

class MainPresenter(val view: MainView) {
    val model = CountersModel()

    fun button1Click(){
        val count = model.next(0)
        model.set(0, count)
        view.setButton1Text(count.toString())
        Log.d("TAG", "count1 = $count")
    }

    fun button2Click(){
        val count = model.next(1)
        model.set(1, count)
        view.setButton2Text(count.toString())
        Log.d("TAG", "count1 = $count")
    }

    fun button3Click(){
        val count = model.next(2)
        model.set(2, count)
        view.setButton3Text(count.toString())
        Log.d("TAG", "count1 = $count")
    }
}
