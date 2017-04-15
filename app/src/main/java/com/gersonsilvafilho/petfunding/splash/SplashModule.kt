package com.gersonsilvafilho.petfunding.splash

import dagger.Module
import dagger.Provides

@Module
class SplashModule(private val splashView: SplashContract.View) {

    @Provides
    internal fun providesSplashContractView(): SplashContract.View {
        return splashView
    }
}