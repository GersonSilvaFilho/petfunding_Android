package com.gersonsilvafilho.petfunding.splash

import com.gersonsilvafilho.petfunding.model.user.UserRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Singleton
@Module
class SplashModule(val mSplashActivity : SplashActivity){
    @Provides
    fun provideSplashPresenter(userRepository: UserRepository): SplashPresenter{
        return SplashPresenter(mSplashActivity, userRepository)
    }
}