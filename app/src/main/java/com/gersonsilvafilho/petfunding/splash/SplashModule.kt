package com.gersonsilvafilho.petfunding.splash

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Singleton
@Module
class SplashModule{

    var mSplashPresenter: SplashPresenter
    constructor() {
        mSplashPresenter = SplashPresenter()
    }

    @Provides
    @Singleton
    fun provideSplashPresenter(): SplashPresenter{
        return mSplashPresenter
    }
}