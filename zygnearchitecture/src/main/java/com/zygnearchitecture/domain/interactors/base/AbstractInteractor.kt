/**
 * Copyright (C) 2017 Bardur Thomsen Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.zygnearchitecture.domain.interactors.base;


import com.zygnearchitecture.domain.executor.base.Executor;
import com.zygnearchitecture.domain.executor.base.MainThread;

/**
 * Base class for all interactors.
 *
 * All interactors should extend this class, and implement their main logic
 * in the method run().
 */
public abstract class AbstractInteractor implements Interactor {

    private static final String TAG = AbstractInteractor.class.getSimpleName();

    // executor for executing the main logic on a background thread.
    private final Executor executor;

    // flag indicating if this interactor is active, is doing work.
    private boolean active;

    // flag to indicate if the this interactor has finished.
    private boolean finished;

    // main thread for posting results from worker thread to the main thread.
    protected final MainThread mainThread;

    public AbstractInteractor(Executor executor, MainThread mainThread){
        this.executor = executor;
        this.mainThread = mainThread;

        active = false;
        finished = false;
    }

    // this method will execute the main logic for the interactor.
    public abstract void run();

    @Override
    public void execute() {

        active = true;
        executor.execute(this);
    }

    public void onFinished() {
        active = false;
        finished = true;
    }

    public boolean isActive(){
        return active;
    }

    public boolean isFinshed(){
        return finished;
    }

}
