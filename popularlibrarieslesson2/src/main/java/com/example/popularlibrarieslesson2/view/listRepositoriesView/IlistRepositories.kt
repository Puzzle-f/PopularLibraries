package com.example.popularlibrarieslesson2.view.listRepositoriesView

interface IListRepositoriesPresenter<V : IRepoItemView> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}

interface IRepositoriesPresenter : IListRepositoriesPresenter<RepoItemView>