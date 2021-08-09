package com.example.popularlibrarieslesson2.view.listRepositoriesView

interface IListRepositories<V : IRepoItemView> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}

interface IListRepositoriesPresenter : IListRepositories<RepoItemView>