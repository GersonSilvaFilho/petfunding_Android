package com.gersonsilvafilho.petfunding.splash

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
    lateinit var authObserverSubs: Disposable

    constructor(view: SplashContract.View, userRepository: UserRepository)
    {
        mUserRepository = userRepository
        mSplashView = view

        setUserStatusSubscriber()
    }

    private fun setUserStatusSubscriber()
    {
        authObserverSubs = mUserRepository.userStatus().subscribe (userSubscriber)
    }

    val userSubscriber = { logged:Boolean -> run {
        if(firebaseIsConnected != logged)
        {
            if(logged)
                userLoginSucess()
            else
                userLogoutSuccess()
            firebaseIsConnected = logged
        }
    }}

    private fun userLoginSucess()
    {
        mSplashView.showFirebaseSuccess()
        mSplashView.goToMainMenuActivity()
        mUserRepository.monitorCurrentUser()
    }

    private fun userLogoutSuccess()
    {
        mSplashView.startSelfActivity()
        authObserverSubs.dispose()
    }

    override fun facebookSuccess()
    {
        mSplashView.facebookSuccess()
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
        mUserRepository.loginWithFacebook(token).take(1).subscribe {
            if(it)
            {
                mUserRepository.getUsernameFromFacebook()
            }
        }
    }


}