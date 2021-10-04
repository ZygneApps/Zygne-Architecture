package com.zygne.zygnearch.domain.presentation.presenters.concrete

import com.zygne.zygnearch.domain.interactors.concrete.MainInteractorImpl
import com.zygne.zygnearch.domain.interactors.virtual.MainInteractor
import com.zygne.zygnearch.domain.presentation.presenters.virtual.MainPresenter
import com.zygnearchitecture.domain.executor.base.Executor
import com.zygnearchitecture.domain.executor.base.MainThread
import com.zygnearchitecture.presentation.presenters.base.AbstractPresenter

class MainPresenterImpl(executor: Executor?, mainThread: MainThread?, private var view: MainPresenter.View?) : AbstractPresenter(executor!!, mainThread!!), MainPresenter, MainInteractor.Callback {
    override fun onMainCompleted() {
        if (view != null) {
            view!!.hideProgress()
            view!!.onMainCompleted()
        }
    }

    override fun resume() {}
    override fun pause() {}
    override fun stop() {}
    override fun destroy() {
        view = null
    }

    override fun start() {
        if (view != null) {
            view!!.showProgress()
            val interactor: MainInteractor = MainInteractorImpl(executor, mainThread, this)
            interactor.execute()
        }
    }
}