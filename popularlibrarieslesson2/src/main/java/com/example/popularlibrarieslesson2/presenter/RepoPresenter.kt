package com.example.popularlibrarieslesson2.presenter

import android.util.Log
import com.example.popularlibrarieslesson2.model.api.GithubUser
import com.example.popularlibrarieslesson2.model.api.IGithubUsersRepo
import com.example.popularlibrarieslesson2.model.api.Repository
import com.example.popularlibrarieslesson2.view.IScreens
import com.example.popularlibrarieslesson2.view.RepoView
import com.example.popularlibrarieslesson2.view.listRepositoriesView.IRepositoriesPresenter
import com.example.popularlibrarieslesson2.view.listRepositoriesView.RepoItemView
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.Disposable
import moxy.MvpPresenter

class RepoPresenter(

    private val userData: GithubUser,
    private val uiScheduler: Scheduler,
    private val usersRepo: IGithubUsersRepo,
//    private val repositoriesRepo: IGithubRepositoriesRepo,
    private val router: Router,
    private val screens: IScreens
) : MvpPresenter<RepoView>() {

    class RepoListPresenter(
        private val router: Router,
        private val screens: IScreens
    ) : IRepositoriesPresenter {
        var repositories = mutableListOf<Repository>()
        override var itemClickListener: ((RepoItemView) -> Unit)? = null

        override fun bindView(view: RepoItemView) {
            val repo = repositories[view.pos]
            Log.d("", "Имя репозитория: ${repo.name}")
            repo.name?.let { view.setRepoName(it) }
//                itemClickListener = {
//                Log.d("", "передаём репозиторий: ${repo}")
//                router.navigateTo(screens.repoInfo(repositories[view.pos]))
//            }
        }

        override fun getCount() = repositories.size
    }

    val repoListPresenter = RepoListPresenter(router, screens)
    private var disposable: Disposable? = null

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
//        loadDataRoom()
        repoListPresenter.itemClickListener = { itemView ->
            val repoData = repoListPresenter.repositories[itemView.pos]
            Log.d("", "передаём репозиторий: $repoData")
            router.navigateTo(screens.repoInfo(repoData))
        }
    }

    private fun loadData() {
        disposable =
            usersRepo.fetchUserRepo(userData.login!!)
                .observeOn(uiScheduler)
                .subscribe({ repos ->
                    repoListPresenter.repositories.clear()
                    repoListPresenter.repositories.addAll(repos)
                    viewState.updateList()
                }, {
                    println("Error : ${it.message}")
                })
    }

//    private fun loadDataRoom(){
//        disposable =
//            repositoriesRepo.getRepositories(name)
//                .observeOn(uiScheduler)
//                .subscribe({repos ->
//                    repoListPresenter.repositories.clear()
//                    repoListPresenter.repositories.addAll(repos)
//                    viewState.updateList()
//                }, {
//                    println("Error : ${it.message}")
//                })
//    }

    override fun onDestroy() {
        super.onDestroy()
        router.exit()
        disposable?.dispose()
    }

    fun backPressed(): Boolean {
        router.exit()
        disposable?.dispose()
        router.navigateTo(screens.users())
        return true
    }
}