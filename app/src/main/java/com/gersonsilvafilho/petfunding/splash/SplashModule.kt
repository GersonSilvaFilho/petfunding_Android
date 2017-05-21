package com.gersonsilvafilho.petfunding.splash

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Singleton
@Module
class SplashModule{

    @Provides
    fun provideSplashPresenter(): SplashPresenter{
        return SplashPresenter()
    }
}