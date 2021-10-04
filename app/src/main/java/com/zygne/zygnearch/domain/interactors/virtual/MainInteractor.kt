package com.zygne.zygnearch.domain.interactors.virtual

import com.zygnearchitecture.domain.interactors.base.Interactor

interface MainInteractor : Interactor {
    interface Callback {
        fun onMainCompleted()
    }
}