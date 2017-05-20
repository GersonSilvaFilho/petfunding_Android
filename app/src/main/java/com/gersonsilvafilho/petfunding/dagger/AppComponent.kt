package com.gersonsilvafilho.petfunding.dagger

import com.gersonsilvafilho.petfunding.model.user.UserModule
import com.gersonsilvafilho.petfunding.splash.SplashActivity
import com.gersonsilvafilho.petfunding.util.PetApplication
import dagger.Component
import javax.inject.Singleton

/**
 * Created by GersonSilva on 5/19/17.
 */
@Singleton
@Component(modules = arrayOf(AppModule::class, UserModule::class))
interface AppComponent {
    fun inject(target: PetApplication)
}