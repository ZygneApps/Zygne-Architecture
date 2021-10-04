package com.zygne.zygnearch.domain.presentation.presenters.virtual

import com.zygnearchitecture.presentation.presenters.base.BasePresenter
import com.zygnearchitecture.presentation.views.base.BaseView

interface MainPresenter : BasePresenter {
    interface View : BaseView {
        fun onMainCompleted()
    }

    fun start()
}