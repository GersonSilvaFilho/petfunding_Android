package com.gersonsilvafilho.petfunding.splash

import com.gersonsilvafilho.petfunding.model.user.UserRepository
import javax.inject.Singleton


/**
 * Created by GersonSilva on 3/21/17.
 */
@Singleton
class SplashPresenter : SplashContract.Presenter  {

    var mUserRepository: UserRepository
    var mSplashView: SplashContract.View

    constructor(view: SplashContract.View, userRepository: UserRepository)
    {
        mUserRepository = userRepository
        mSplashView = view

        setUserStatusSubscriber()
    }

    private fun setUserStatusSubscriber()
    {
         mUserRepository.userStatus().firstOrError().subscribe (userSubscriber)
    }

    val userSubscriber = { logged:Boolean -> run {
        if(logged)
            userLoginSucess()
    }}

    private fun userLoginSucess()
    {
        mSplashView.showFirebaseSuccess()
        mSplashView.goToMainMenuActivity()
        mUserRepository.monitorCurrentUser()
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
                userLoginSucess()
            }
        }
    }


}