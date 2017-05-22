package com.gersonsilvafilho.petfunding.splash

import android.util.Log
import com.gersonsilvafilho.petfunding.model.user.UserRepository
import io.reactivex.disposables.Disposable
import javax.inject.Singleton


/**
 * Created by GersonSilva on 3/21/17.
 */
@Singleton
class SplashPresenter : SplashContract.Presenter  {

    var mUserRepository: UserRepository
    var mSplashView: SplashContract.View

    private var firebaseIsConnected:Boolean = false
    private var  authObserverSubs: Disposable

    constructor(view: SplashContract.View, userRepository: UserRepository)
    {
        mUserRepository = userRepository
        mSplashView = view
        authObserverSubs = mUserRepository.userStatus().subscribe { logged -> run {
            if(firebaseIsConnected != logged)
            {
                if(logged)
                {
                    mSplashView.showFirebaseSuccess()
                    mSplashView.goToMainMenuActivity()
                    mUserRepository.monitorCurrentUser()
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
        mUserRepository.loginWithFacebook(token).take(1).subscribe { mUserRepository.getUsernameFromFacebook() }
    }


}