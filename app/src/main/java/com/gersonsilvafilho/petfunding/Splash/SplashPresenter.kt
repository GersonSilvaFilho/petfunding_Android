package com.gersonsilvafilho.petfunding.Splash

import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.gersonsilvafilho.petfunding.splash.SplashContract


/**
 * Created by GersonSilva on 3/21/17.
 */
class SplashPresenter constructor(var mSplashView: SplashContract.View) : SplashContract.Presenter  {


    override fun facebookSuccess(loginResult: LoginResult)
    {

    }

    override fun facebookCancel()
    {

    }

    override fun facebokkOnError(error: FacebookException)
    {

    }
    override fun firebaseSuccess()
    {
        mSplashView.showFirebaseSuccess()
    }


}