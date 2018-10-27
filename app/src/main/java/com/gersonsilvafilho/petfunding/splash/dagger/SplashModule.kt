package com.gersonsilvafilho.petfunding.splash.dagger

import com.gersonsilvafilho.petfunding.model.user.UserRepository
import com.gersonsilvafilho.petfunding.splash.ui.SplashActivity
import com.gersonsilvafilho.petfunding.splash.ui.SplashPresenter
import dagger.Module
import dagger.Provides

@Module
class SplashModule(private val mSplashActivity: SplashActivity) {
    @Provides
    fun provideSplashPresenter(userRepository: UserRepository): SplashPresenter {
        return SplashPresenter(mSplashActivity, userRepository)
    }
}