package com.zygne.zygnearch.domain.presentation.presenters.virtual;


import com.zygnearchitecture.presentation.presenters.base.BasePresenter;
import com.zygnearchitecture.presentation.views.base.BaseView;

public interface MainPresenter extends BasePresenter {

    interface View extends BaseView {
        void onMainCompleted();
    }

    void start();
}
