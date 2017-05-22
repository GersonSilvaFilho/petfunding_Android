package com.gersonsilvafilho.petfunding.main

import com.gersonsilvafilho.petfunding.util.ActivityScope
import dagger.Subcomponent


/**
 * Created by GersonSilva on 4/10/17.
 */
@ActivityScope
@Subcomponent(modules = arrayOf(MainMenuModule::class))
interface MainMenuComponent {
    fun inject(activity: MainMenuActivity)
}