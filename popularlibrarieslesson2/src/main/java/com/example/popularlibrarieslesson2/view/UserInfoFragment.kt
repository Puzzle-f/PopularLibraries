package com.example.popularlibrarieslesson2.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.popularlibrarieslesson2.R
import com.example.popularlibrarieslesson2.databinding.FragmentUserInfoBinding
import com.example.popularlibrarieslesson2.fragment.UsersFragment
import com.example.popularlibrarieslesson2.model.GithubUser
import moxy.MvpAppCompatFragment


class UserInfoFragment(private val userData: GithubUser) : MvpAppCompatFragment() {

    private var vb: FragmentUserInfoBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentUserInfoBinding.inflate(inflater, container, false).also {
        vb = it
        setLoginText()
    }.root

    companion object {
        fun newInstance(userData: GithubUser) = UserInfoFragment(userData)
    }

    fun setLoginText(){
        vb?.loginText?.text = userData.login
    }

}