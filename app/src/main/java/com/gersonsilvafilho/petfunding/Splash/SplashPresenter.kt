package com.gersonsilvafilho.petfunding.Splash

/**
 * Created by GersonSilva on 3/21/17.
 */
class SplashPresenter(splashView: SplashContract.View){

    private val mSplashView: SplashContract.View

    init {
        mSplashView = splashView

        splashView.onLoginClick().subscribe { login() }
    }


    private fun login()
    {
        mSplashView.showToast()
    }
}