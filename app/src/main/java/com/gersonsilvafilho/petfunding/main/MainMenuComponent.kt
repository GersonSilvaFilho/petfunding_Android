package com.gersonsilvafilho.petfunding.main

import dagger.Component


/**
 * Created by GersonSilva on 4/10/17.
 */

@Component(modules = arrayOf(MainMenuModule::class))
interface MainMenuComponent {
    fun inject(activity: MainMenuActivity)
}