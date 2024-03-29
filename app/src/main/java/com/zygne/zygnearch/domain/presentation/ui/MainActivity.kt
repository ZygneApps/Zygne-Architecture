package com.zygne.zygnearch.domain.presentation.ui

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.zygne.zygnearch.R
import com.zygne.zygnearch.domain.presentation.presenters.concrete.MainPresenterImpl
import com.zygne.zygnearch.domain.presentation.presenters.virtual.MainPresenter
import com.zygnearchitecture.domain.executor.implementation.ThreadExecutor
import com.zygnearchitecture.domain.log.factory.LoggerFactory
import com.zygnearchitecture.domain.log.implementation.DebugLogger
import com.zygnearchitecture.threads.AndroidThread

class MainActivity : AppCompatActivity(), MainPresenter.View {
    private lateinit var presenter: MainPresenter
    private lateinit var tvMain: TextView
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvMain = findViewById(R.id.tv_main)
        progressBar = findViewById(R.id.progress_bar)

        LoggerFactory.setDefaultLogger(DebugLogger())
        presenter = MainPresenterImpl(
            ThreadExecutor,
            AndroidThread,
            this
        )


        presenter.start()
    }

    override fun onMainCompleted() {
        tvMain.text = "Presenter has finished"
    }

    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressBar.visibility = View.GONE
    }
}