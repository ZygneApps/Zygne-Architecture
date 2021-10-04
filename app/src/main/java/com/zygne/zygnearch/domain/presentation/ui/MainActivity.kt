package com.zygne.zygnearch.domain.presentation.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.zygne.zygnearch.R;
import com.zygne.zygnearch.domain.presentation.presenters.concrete.MainPresenterImpl;
import com.zygne.zygnearch.domain.presentation.presenters.virtual.MainPresenter;
import com.zygnearchitecture.domain.executor.implementation.ThreadExecutor;
import com.zygnearchitecture.threads.AndroidThread;

public class MainActivity extends AppCompatActivity implements
        MainPresenter.View {

    private MainPresenter presenter;

    private TextView tvMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvMain = findViewById(R.id.tv_main);

        presenter = new MainPresenterImpl(ThreadExecutor.getInstance(),
                AndroidThread.getInstance(),
                this);

        presenter.start();
    }

    @Override
    public void onMainCompleted() {

        tvMain.setText("Presenter has finished");
    }

    @Override
    public void showProgress() {
        tvMain.setText("Presenter has started");
    }

    @Override
    public void hideProgress() {

    }
}

