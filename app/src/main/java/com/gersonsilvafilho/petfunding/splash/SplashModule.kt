package com.gersonsilvafilho.petfunding.splash

import com.gersonsilvafilho.petfunding.model.UserRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Singleton
@Module
class SplashModule{

    var mSplashPresenter: SplashPresenter

    constructor(splashView: SplashContract.View, userRepository: UserRepository) {
        mSplashPresenter = SplashPresenter(splashView, userRepository)
    }

    @Provides
    @Singleton
    fun provideSplashPresenter(): SplashPresenter{
        return mSplashPresenter
    }
}