package com.example.popularlibrarieslesson2.view.listRepositoriesView

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.popularlibrarieslesson2.databinding.FragmentRepoInfoBinding
import com.example.popularlibrarieslesson2.databinding.FragmentUsersBinding
import com.example.popularlibrarieslesson2.view.BackButtonListener
import com.example.popularlibrarieslesson2.view.UsersFragment
import moxy.MvpAppCompatFragment

class RepoInfoFragment:  MvpAppCompatFragment(), BackButtonListener {

    companion object {
        fun newInstance() = RepoInfoFragment()
    }

    private var vb: FragmentRepoInfoBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        FragmentRepoInfoBinding.inflate(inflater, container, false).also {
            vb = it
        }.root

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }

    override fun backPressed(): Boolean {
        TODO("Not yet implemented")
    }


}