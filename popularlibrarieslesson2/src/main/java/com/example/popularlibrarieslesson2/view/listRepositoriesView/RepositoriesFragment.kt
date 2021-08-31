package com.example.popularlibrarieslesson2.view.listRepositoriesView

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.popularlibrarieslesson2.App
import com.example.popularlibrarieslesson2.databinding.FragmentRepositoriesBinding
import com.example.popularlibrarieslesson2.model.api.*
import com.example.popularlibrarieslesson2.model.api.RoomData.db.Database
import com.example.popularlibrarieslesson2.presenter.RepoPresenter
import com.example.popularlibrarieslesson2.view.AndroidScreens
import com.example.popularlibrarieslesson2.view.BackButtonListener
import com.example.popularlibrarieslesson2.view.IScreens
import com.example.popularlibrarieslesson2.view.RepoView
import com.example.popularlibrarieslesson2.view.network.AndroidNetworkStatus
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class RepositoriesFragment(private val userData: GithubUser) : MvpAppCompatFragment(), RepoView, BackButtonListener {

    companion object {
        fun newInstance(userData: GithubUser) = RepositoriesFragment(userData)
    }


    val presenter: RepoPresenter by moxyPresenter {
        Log.d("", "инициилизируем presenter для RepositoriesFragment")
        RepoPresenter(
            userData,
            AndroidSchedulers.mainThread(),
            RetrofitGithubRepositoriesRepo(
                ApiHolder.api,
                AndroidNetworkStatus(requireContext()),
                Database.getInstance()
            ),
            App.instance.router,
            AndroidScreens()
        )
    }

    var adapter: RepositoriesRVAdapter? = null
    private var vb: FragmentRepositoriesBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentRepositoriesBinding.inflate(inflater, container, false).also {
        vb = it
    }.root

    override fun onDestroy() {
        super.onDestroy()
        vb = null
    }

    override fun init() {
        vb?.rvRepos?.layoutManager = LinearLayoutManager(context)
        adapter = RepositoriesRVAdapter(presenter.repoListPresenter)
        vb?.rvRepos?.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed(): Boolean {
        presenter.backPressed()
        return true
    }
}