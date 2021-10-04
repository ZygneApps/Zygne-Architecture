package com.zygne.zygnearch.domain.presentation.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.zygne.zygnearch.R
import com.zygne.zygnearch.domain.presentation.presenters.concrete.MainPresenterImpl
import com.zygne.zygnearch.domain.presentation.presenters.virtual.MainPresenter
import com.zygnearchitecture.domain.executor.implementation.ThreadExecutor
import com.zygnearchitecture.threads.AndroidThread

class MainActivity : AppCompatActivity(), MainPresenter.View {
    private lateinit var presenter: MainPresenter
    private var tvMain: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvMain = findViewById(R.id.tv_main)

        presenter = MainPresenterImpl(ThreadExecutor,
                AndroidThread,
                this)


        presenter.start()
    }

    override fun onMainCompleted() {
        tvMain!!.text = "Presenter has finished"
    }

    override fun showProgress() {
        tvMain!!.text = "Presenter has started"
    }

    override fun hideProgress() {}
}