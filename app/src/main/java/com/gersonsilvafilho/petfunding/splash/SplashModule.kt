package com.gersonsilvafilho.petfunding.splash

import dagger.Module
import dagger.Provides


@Module
class SplashModule(val splashView: SplashContract.View) {

    @Provides
    fun provideSplashPresenter(): SplashPresenter{
        return  SplashPresenter(splashView)
    }
}