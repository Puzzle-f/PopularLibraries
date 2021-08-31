package com.example.popularlibrarieslesson2.view.listRepositoriesView

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.popularlibrarieslesson2.databinding.FragmentRepoInfoBinding
import com.example.popularlibrarieslesson2.databinding.FragmentUsersBinding
import com.example.popularlibrarieslesson2.model.api.Repository
import com.example.popularlibrarieslesson2.presenter.RepoInfoPresenter
import com.example.popularlibrarieslesson2.view.BackButtonListener
import com.example.popularlibrarieslesson2.view.UsersFragment
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class RepoInfoFragment(repository: Repository):  MvpAppCompatFragment(), BackButtonListener {

    companion object {
        fun newInstance(repository: Repository) = RepoInfoFragment(repository)
    }

    var textRepo = repository.name

    private var vb: FragmentRepoInfoBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        FragmentRepoInfoBinding.inflate(inflater, container, false).also {
            vb = it
            vb?.forks?.text = textRepo
        }.root

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }

    override fun backPressed(): Boolean {
        return true
    }


}