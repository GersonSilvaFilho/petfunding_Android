package com.gersonsilvafilho.petfunding.main.dagger

import com.gersonsilvafilho.petfunding.main.ui.MainMenuActivity
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