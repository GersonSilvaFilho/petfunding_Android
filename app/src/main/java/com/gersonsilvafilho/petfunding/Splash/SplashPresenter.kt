package com.gersonsilvafilho.petfunding.splash

import com.facebook.FacebookException
import com.facebook.login.LoginResult


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
        mSplashView.showFacebookError()
    }
    override fun firebaseSuccess()
    {
        mSplashView.showFirebaseSuccess()
    }


}