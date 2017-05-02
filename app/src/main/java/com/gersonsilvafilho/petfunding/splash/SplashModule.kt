package com.gersonsilvafilho.petfunding.splash

import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Singleton
@Module
class SplashModule{

    var mSplashView: SplashContract.View
    var mAuth: FirebaseAuth
    var mSplashPresenter: SplashPresenter

    constructor(splashView: SplashContract.View, auth: FirebaseAuth) {
        mSplashView = splashView
        mAuth = auth
        mSplashPresenter = SplashPresenter(mSplashView, mAuth)
    }

    @Provides
    @Singleton
    fun provideSplashPresenter(): SplashPresenter{
        return mSplashPresenter
    }
}