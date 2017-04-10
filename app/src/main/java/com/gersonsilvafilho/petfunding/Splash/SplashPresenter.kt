package com.gersonsilvafilho.petfunding.Splash

import com.facebook.FacebookException
import com.facebook.login.LoginResult


/**
 * Created by GersonSilva on 3/21/17.
 */
class SplashPresenter(splashView: SplashContract.View){





    private val mSplashView: SplashContract.View




    init {
        mSplashView = splashView



    }

    fun facebookSuccess(loginResult: LoginResult)
    {

    }

    fun facebookCancel()
    {

    }

    fun facebokkOnError(error: FacebookException)
    {

    }
    fun firebaseSuccess()
    {
        mSplashView.showFirebaseSuccess()
    }


}