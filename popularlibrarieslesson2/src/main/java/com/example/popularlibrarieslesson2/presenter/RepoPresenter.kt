package com.example.popularlibrarieslesson2.presenter

import android.util.Log
import com.example.popularlibrarieslesson2.model.api.IGithubUsersRepo
import com.example.popularlibrarieslesson2.model.api.Repository
import com.example.popularlibrarieslesson2.view.IScreens
import com.example.popularlibrarieslesson2.view.RepoView
import com.example.popularlibrarieslesson2.view.UsersView
import com.example.popularlibrarieslesson2.view.listRepositoriesView.IRepositoriesPresenter
import com.example.popularlibrarieslesson2.view.listRepositoriesView.RepoItemView
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.Disposable
import moxy.MvpPresenter

class RepoPresenter(
    private val name: String,
    private val uiScheduler: Scheduler,
    private val usersRepo: IGithubUsersRepo,
    private val router: Router,
    private val screens: IScreens
) : MvpPresenter<RepoView>() {

    class RepoListPresenter : IRepositoriesPresenter {
        var repositories = mutableListOf<Repository>()
        override var itemClickListener: ((RepoItemView) -> Unit)? = null

        override fun bindView(view: RepoItemView) {
            val repo = repositories[view.pos]
            repo.name?.let{view.setRepoName(it)}
        }
        override fun getCount() = repositories.size
    }

    val repoListPresenter = RepoListPresenter()
    private var disposable: Disposable? = null

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
//        repoListPresenter.itemClickListener = { itemView ->
//            val repoData = repoListPresenter.repositories[itemView.pos]
//            Log.d("", "Имя репозитория: $repoData")
//            router.navigateTo(screens.repoInfo())
//        }
    }

    private fun loadData(){
            disposable =
                usersRepo.fetchUserRepo(name)
                    .observeOn(uiScheduler)
                    .subscribe({repos ->
                        repoListPresenter.repositories.clear()
                        repoListPresenter.repositories.addAll(repos)
                        viewState.updateList()
                    }, {
                        println("Error : ${it.message}")
                    })
        }

    override fun onDestroy() {
        super.onDestroy()
        router.exit()
        disposable?.dispose()
    }

    fun backPressed(): Boolean{
        router.exit()
        disposable?.dispose()
        router.navigateTo(screens.users())
        return true
    }
}