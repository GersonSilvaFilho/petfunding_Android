package com.gersonsilvafilho.petfunding.splash

import dagger.Component
import javax.inject.Singleton


/**
 * Created by GersonSilva on 4/10/17.
 */

@Singleton
@Component(modules = arrayOf(SplashModule::class))
interface SplashComponent {
    fun inject(activity: SplashActivity)
}