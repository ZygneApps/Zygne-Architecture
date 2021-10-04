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

package com.zygnearchitecture.domain.executor.implementation;


import android.util.Log;


import com.zygnearchitecture.domain.executor.base.Executor;
import com.zygnearchitecture.domain.interactors.base.AbstractInteractor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Executor class for execution jobs on a background thread.
 * This class makes sure that the interactors will get a background thread to run on.
 */
public class ThreadExecutor implements Executor {

    private static final String TAG = ThreadExecutor.class.getSimpleName();

    private static volatile ThreadExecutor threadExecutor;

    private static final int                     CORE_POOL_SIZE  = 5;
    private static final int                     MAX_POOL_SIZE   = 7;
    private static final long                    TIME_TO_LIVE    = 120;
    private static final BlockingQueue<Runnable> WORK_QUEUE      = new LinkedBlockingQueue<>();

    private final ThreadPoolExecutor threadPoolExecutor;

    private ThreadExecutor() {
        threadPoolExecutor = new ThreadPoolExecutor(
                CORE_POOL_SIZE,
                MAX_POOL_SIZE,
                TIME_TO_LIVE,
                TimeUnit.SECONDS,
                WORK_QUEUE);
    }

    public static Executor getInstance() {
        if (threadExecutor == null) {
            threadExecutor = new ThreadExecutor();
        }

        return threadExecutor;
    }

    @Override
    public void execute(final AbstractInteractor interactor) {


        // Make sure that the interactor gets executed on a worker thread.
        threadPoolExecutor.submit(new Runnable() {
            @Override
            public void run() {

                long startTime = System.currentTimeMillis();

                // Execute the main logic for the interactor.
                interactor.run();

                // Complete this interactor.
                interactor.onFinished();

                long totalTime = System.currentTimeMillis() - startTime;

                Log.d(TAG, "Time to execute -> " + interactor.getClass().getSimpleName() + ": " + totalTime + " ms");
            }
        });
    }

}
