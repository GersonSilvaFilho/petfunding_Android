package com.gersonsilvafilho.library.presenter

import com.gersonsilvafilho.library.util.network.NetworkManager
import com.gersonsilvafilho.library.view.View
/**
 * Created by GersonSilva on 4/10/17.
 */

abstract class BasePresenter<VIEW : View>(protected var networkManager: NetworkManager) {

    protected var view: VIEW? = null

    fun attachView(view: VIEW) {
        this.view = view
        onViewAttached()
        networkManager.add(toString(), refreshData())
    }

    fun detachView() {
        networkManager.remove(toString())
        onViewDetached()
        this.view = null
    }

    fun resume() {}

    fun pause() {}

    fun destroy() {
        onDestroyed()
    }

    abstract fun refreshData(): () -> Unit

    protected fun onViewAttached() {}

    protected fun onViewDetached() {}

    protected fun onDestroyed() {}
}
