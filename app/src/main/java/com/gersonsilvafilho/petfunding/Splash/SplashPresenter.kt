package com.gersonsilvafilho.petfunding.splash

import android.util.Log
import com.gersonsilvafilho.petfunding.model.UserRepository
import io.reactivex.disposables.Disposable
import javax.inject.Singleton


/**
 * Created by GersonSilva on 3/21/17.
 */
@Singleton
class SplashPresenter  : SplashContract.Presenter  {

    var mSplashView: SplashContract.View
    var mUserRepository: UserRepository

    private var firebaseIsConnected:Boolean = false
    private var  authObserverSubs: Disposable

    constructor(splashView: SplashContract.View, userRepository:UserRepository)
    {
        mSplashView = splashView
        mUserRepository = userRepository

        authObserverSubs = mUserRepository.userStatus().subscribe { logged -> run {
            if(firebaseIsConnected != logged)
            {
                if(logged)
                {
                    mSplashView.showFirebaseSuccess()
                    mSplashView.goToMainMenuActivity()
                }
                else
                {
                    mSplashView.startSelfActivity()
                    authObserverSubs.dispose()
                }
                firebaseIsConnected = logged
            }
            Log.i("Rxfirebase2", "User logged " + logged)
        } }
    }

    override fun facebookSuccess()
    {

    }

    override fun facebookCancel()
    {
        mSplashView.showFacebookError()
    }

    override fun facebookOnError()
    {
        mSplashView.showFacebookError()
    }

    override fun firebaseSuccess(token: String)
    {
        mUserRepository.loginWithFacebook(token).take(1).subscribe {  }
    }


}