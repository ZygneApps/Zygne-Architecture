package com.zygne.zygnearch.domain.presentation.presenters.concrete;


import com.zygne.zygnearch.domain.interactors.concrete.MainInteractorImpl;
import com.zygne.zygnearch.domain.interactors.virtual.MainInteractor;
import com.zygne.zygnearch.domain.presentation.presenters.virtual.MainPresenter;
import com.zygnearchitecture.domain.executor.base.Executor;
import com.zygnearchitecture.domain.executor.base.MainThread;
import com.zygnearchitecture.presentation.presenters.base.AbstractPresenter;


public class MainPresenterImpl extends AbstractPresenter implements
        MainPresenter,
        MainInteractor.Callback {

    private View view;

    public MainPresenterImpl(Executor executor, MainThread mainThread, View view) {
        super(executor, mainThread);

        this.view = view;
    }

    @Override
    public void onMainCompleted() {
        if(view != null){
            view.hideProgress();
            view.onMainCompleted();
        }
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void destroy() {
        view = null;
    }

    @Override
    public void start() {
        if(view != null){
            view.showProgress();

            MainInteractor interactor =
                    new MainInteractorImpl(executor, mainThread, this);

            interactor.execute();
        }
    }
}
