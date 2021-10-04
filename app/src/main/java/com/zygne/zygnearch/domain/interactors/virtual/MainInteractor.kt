package com.zygne.zygnearch.domain.interactors.virtual;


import com.zygnearchitecture.domain.interactors.base.Interactor;

public interface MainInteractor extends Interactor {

    interface Callback {
        void onMainCompleted();
    }
}
