package com.gersonsilvafilho.petfunding.splash.dagger

import com.gersonsilvafilho.petfunding.model.user.UserRepository
import com.gersonsilvafilho.petfunding.splash.ui.SplashActivity
import com.gersonsilvafilho.petfunding.splash.ui.SplashPresenter
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module
class SplashActivityModule {
    @Provides
    fun provideSplashPresenter(splashActivity: SplashActivity, userRepository: UserRepository): SplashPresenter {
        return SplashPresenter(splashActivity, userRepository)
    }
}

@Module
abstract class SplashActivityBuilderModule {
    @ContributesAndroidInjector(modules = [SplashActivityModule::class])
    abstract fun contributeVoucherActivity(): SplashActivity
}