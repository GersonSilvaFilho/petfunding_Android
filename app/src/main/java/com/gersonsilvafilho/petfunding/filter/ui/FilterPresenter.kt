package com.gersonsilvafilho.petfunding.splash.ui

import io.reactivex.disposables.CompositeDisposable

/**
 * Created by GersonSilva on 3/21/17.
 */
class FilterPresenter(private val view: FilterContract.View) : FilterContract.Presenter {

    private val compositeDisposable = CompositeDisposable()

    init {

        compositeDisposable.add(
            view.applyButtonClicked()
                .subscribe {
                    view.finishFilter()
                }
        )


    }

    override fun onStop() {
        compositeDisposable.clear()
    }

}