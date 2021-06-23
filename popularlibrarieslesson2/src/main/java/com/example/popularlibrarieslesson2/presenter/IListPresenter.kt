package com.example.popularlibrarieslesson2.presenter

import com.example.popularlibrarieslesson2.view.IItemView
import com.example.popularlibrarieslesson2.view.UserItemView

interface IListPresenter<V : IItemView> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}

interface IUserListPresenter : IListPresenter<UserItemView>
