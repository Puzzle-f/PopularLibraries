package com.example.popularlibrarieslesson2.view.listRepositoriesView

interface IRepoItemView {
    var pos: Int
}

interface RepoItemView : IRepoItemView {
    fun setRepoName(text: String)
}