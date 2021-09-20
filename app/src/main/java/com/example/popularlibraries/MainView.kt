package com.example.popularlibraries

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)   // стратегия Moxy
interface MainView : MvpView{

    // для каждой команды можно указывать отдельную стратегию
    fun setButton1Text(text: String)
    // если стратегия не указана, к ней применяется стратегия класса
    fun setButton2Text(text: String)

    fun setButton3Text(text: String)
}
