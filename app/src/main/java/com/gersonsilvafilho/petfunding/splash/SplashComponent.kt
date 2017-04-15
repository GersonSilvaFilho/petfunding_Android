package com.gersonsilvafilho.petfunding.splash

import dagger.Component


/**
 * Created by GersonSilva on 4/10/17.
 */

@Component(modules = arrayOf(SplashModule::class))
interface SplashComponent {
    fun inject(activity: SplashActivity)
}