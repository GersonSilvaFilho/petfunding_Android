package com.gersonsilvafilho.petfunding.splash

import com.gersonsilvafilho.petfunding.model.user.UserModule
import dagger.Component
import javax.inject.Singleton


/**
 * Created by GersonSilva on 4/10/17.
 */

@Singleton
@Component(modules = arrayOf(UserModule::class,SplashModule::class))
interface SplashComponent {
    fun inject(activity: SplashActivity)
    fun inject(presenter: SplashPresenter)
}