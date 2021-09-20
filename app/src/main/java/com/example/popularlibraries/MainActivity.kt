package com.example.popularlibraries

import android.os.Bundle
import android.view.View
import com.example.popularlibraries.databinding.ActivityMainBinding
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

class MainActivity : MvpAppCompatActivity(), MainView {

    private var vb: ActivityMainBinding? = null

    val presenter by moxyPresenter {
        MainPresenter(CountersModel())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb?.root)

        vb?.btnCounter1?.setOnClickListener { presenter.button1Click() }
        vb?.btnCounter2?.setOnClickListener { presenter.button2Click() }
        vb?.btnCounter3?.setOnClickListener { presenter.button3Click() }

    }

    override fun setButton1Text(text: String) {
            vb?.btnCounter1?.text = text
    }

    override fun setButton2Text(text: String) {
            vb?.btnCounter2?.text = text
    }

    override fun setButton3Text(text: String) {
        vb?.btnCounter3?.text = text
    }


}
