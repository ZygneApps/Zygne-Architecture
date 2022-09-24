package com.zygne.zygnearch.domain.interactors.concrete

import com.zygne.zygnearch.domain.interactors.virtual.MainInteractor
import com.zygnearchitecture.domain.executor.base.Executor
import com.zygnearchitecture.domain.executor.base.MainThread
import com.zygnearchitecture.domain.interactors.base.AbstractInteractor

class MainInteractorImpl(
    executor: Executor?, mainThread: MainThread?,
    private val callback: MainInteractor.Callback
) : AbstractInteractor(executor!!, mainThread!!), MainInteractor {
    override fun run() {
        try {
            Thread.sleep(4500)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        mainThread.post { callback.onMainCompleted() }
    }
}