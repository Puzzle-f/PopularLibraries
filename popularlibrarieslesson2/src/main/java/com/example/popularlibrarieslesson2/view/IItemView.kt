package com.example.popularlibrarieslesson2.view

interface IItemView {
    var pos: Int
}

interface UserItemView: IItemView {
    fun setLogin(text: String)
    fun loadAvatar(url: String)
}

