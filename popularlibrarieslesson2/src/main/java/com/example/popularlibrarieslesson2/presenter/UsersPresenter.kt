package com.example.popularlibrarieslesson2.presenter

import android.util.Log
import com.example.popularlibrarieslesson2.model.GithubUser
import com.example.popularlibrarieslesson2.model.GithubUsersRepo
import com.example.popularlibrarieslesson2.view.IScreens
import com.example.popularlibrarieslesson2.view.UserItemView
import com.example.popularlibrarieslesson2.view.UsersView
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import moxy.MvpPresenter


class UsersPresenter(
    val usersRepo: GithubUsersRepo,
    val router: Router,
    private val screens: IScreens
) : MvpPresenter<UsersView>() {
    class UsersListPresenter : IUserListPresenter {
        val users = mutableListOf<GithubUser>()
        override var itemClickListener: ((UserItemView) -> Unit)? = null

        override fun getCount() = users.size

        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            user.login?.let { view.setLogin(it) }
        }
    }

    var disposable: Disposable? = null

//    Альтернатива методу execLambda()
//    val githubObserver = object : Observer<GithubUser> {
//        override fun onSubscribe(d: Disposable?) {
//            viewState.init()
//        }
//
//        override fun onNext(t: GithubUser) {
//            usersListPresenter.users.add(t)
//        }
//
//        override fun onError(e: Throwable?) {
//            usersListPresenter.users.add(GithubUser("Ошибка получения данных!!!"))
//        }
//
//        override fun onComplete() {
//        }
//    }
//
//    fun subscribeToGithubUsersRepo() {
//        usersRepo.repositoriesObservable()
//            .subscribe(githubObserver)
//    }

    fun execLambda() {
       disposable = usersRepo.repositoriesObservable().subscribe(
            { s ->
                usersListPresenter.users.add(s)
            },
            { usersListPresenter.users.add(GithubUser("Ошибка получения данных!!!")) },
        )
    }


    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
//        subscribeToGithubUsersRepo()
        execLambda()

        usersListPresenter.itemClickListener = { itemView ->
            val userData = usersListPresenter.users[itemView.pos]
            Log.d("", "Логин пользователя $userData")
            router.navigateTo(screens.userInfo(userData))
        }
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    override fun onDestroy() {
        disposable?.dispose()
        super.onDestroy()
    }
}
