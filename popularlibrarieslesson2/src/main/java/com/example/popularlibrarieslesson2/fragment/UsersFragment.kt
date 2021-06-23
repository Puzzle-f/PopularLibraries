package com.example.popularlibrarieslesson2.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.popularlibrarieslesson2.App
import com.example.popularlibrarieslesson2.view.BackButtonListener
import com.example.popularlibrarieslesson2.model.GithubUsersRepo
import com.example.popularlibrarieslesson2.presenter.UsersPresenter
import com.example.popularlibrarieslesson2.view.UsersRVAdapter
import com.example.popularlibrarieslesson2.view.UsersView
import com.example.popularlibrarieslesson2.databinding.FragmentUsersBinding
import com.example.popularlibrarieslesson2.model.GithubUser
import com.example.popularlibrarieslesson2.view.AndroidScreens
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {
    companion object {
        fun newInstance() = UsersFragment()
    }

    val presenter: UsersPresenter by moxyPresenter { UsersPresenter(GithubUsersRepo(), App.instance.router, AndroidScreens()) }
    var adapter: UsersRVAdapter? = null

    private var vb: FragmentUsersBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        FragmentUsersBinding.inflate(inflater, container, false).also {
            vb = it
        }.root

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }

    override fun init() {
        vb?.rvUsers?.layoutManager = LinearLayoutManager(context)
        adapter = UsersRVAdapter(presenter.usersListPresenter)
        vb?.rvUsers?.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed() = presenter.backPressed()
}
