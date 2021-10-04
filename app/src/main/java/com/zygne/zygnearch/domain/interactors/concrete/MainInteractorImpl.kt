package com.zygne.zygnearch.domain.interactors.concrete;


import com.zygne.zygnearch.domain.interactors.virtual.MainInteractor;
import com.zygnearchitecture.domain.executor.base.Executor;
import com.zygnearchitecture.domain.executor.base.MainThread;
import com.zygnearchitecture.domain.interactors.base.AbstractInteractor;

public class MainInteractorImpl extends AbstractInteractor implements
        MainInteractor {

    private Callback callback;

    public MainInteractorImpl(Executor executor, MainThread mainThread, Callback callback) {
        super(executor, mainThread);

        this.callback = callback;
    }

    @Override
    public void run() {

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        mainThread.post(()-> callback.onMainCompleted());

    }
}
