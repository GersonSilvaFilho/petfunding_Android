package com.gersonsilvafilho.petfunding.main

import com.gersonsilvafilho.petfunding.model.pet.PetModule
import com.gersonsilvafilho.petfunding.model.user.UserModule
import dagger.Component
import javax.inject.Singleton


/**
 * Created by GersonSilva on 4/10/17.
 */
@Singleton
@Component(modules = arrayOf(UserModule::class, PetModule::class, MainMenuModule::class))
interface MainMenuComponent {
    fun inject(activity: MainMenuActivity)
    fun inject(presenter: MainMenuPresenter)
}