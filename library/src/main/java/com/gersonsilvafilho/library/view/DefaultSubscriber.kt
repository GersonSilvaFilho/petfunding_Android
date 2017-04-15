package com.gersonsilvafilho.library.view

import rx.Subscriber

open class DefaultSubscriber<T>(private val view: View?) : Subscriber<T>() {

    override fun onCompleted() {
        view?.hideProgress()
    }

    override fun onError(e: Throwable) {

    }

    override fun onNext(t: T) {

    }
}
