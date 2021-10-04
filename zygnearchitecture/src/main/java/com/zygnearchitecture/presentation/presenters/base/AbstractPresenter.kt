/**
 * Copyright (C) 2017 Bardur Thomsen Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.zygnearchitecture.presentation.presenters.base

import com.zygnearchitecture.domain.executor.base.Executor
import com.zygnearchitecture.domain.executor.base.MainThread

/**
 * Base class all presenters
 *
 * All presenters should extend this class, and pass the member
 * parameters to the interactors which should run.
 *
 * @property executor : implementation of Executor
 * @property mainThread : implementation of MainThread
 */
abstract class AbstractPresenter(protected val executor: Executor,
                                 protected val mainThread: MainThread) {

}