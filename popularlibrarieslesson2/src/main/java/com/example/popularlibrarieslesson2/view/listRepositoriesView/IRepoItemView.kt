package com.example.popularlibrarieslesson2.view.listRepositoriesView

import com.example.popularlibrarieslesson2.model.api.Repository

interface IRepoItemView {
    var pos: Int
}

interface RepoItemView : IRepoItemView {
    fun setRepoName(text: String)
}


