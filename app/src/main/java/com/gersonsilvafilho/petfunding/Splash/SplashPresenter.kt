package com.gersonsilvafilho.petfunding.splash

import com.facebook.FacebookException


/**
 * Created by GersonSilva on 3/21/17.
 */
class SplashPresenter constructor(var mSplashView: SplashContract.View) : SplashContract.Presenter  {


    override fun facebookSuccess(accessToken:String)
    {

    }

    override fun facebookCancel()
    {
        mSplashView.showFacebookError()
    }

    override fun facebookOnError(error: FacebookException)
    {
        mSplashView.showFacebookError()
    }
    override fun firebaseSuccess()
    {
        mSplashView.showFirebaseSuccess()
    }


}