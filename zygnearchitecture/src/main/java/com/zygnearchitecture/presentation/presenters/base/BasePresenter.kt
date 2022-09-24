package com.zygnearchitecture.presentation.presenters.base

/**
 * Interface for Base Presenter
 *
 * this interface contains lifecycle methods which should be implemented
 * by all presenters
 */
interface BasePresenter {
    fun resume()
    fun pause()
    fun stop()
    fun destroy()
}