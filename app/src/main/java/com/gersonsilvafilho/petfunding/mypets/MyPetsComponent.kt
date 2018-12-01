package com.gersonsilvafilho.petfunding.mypets

import com.gersonsilvafilho.petfunding.util.ActivityScope
import dagger.Subcomponent

/**
 * Created by GersonSilva on 5/22/17.
 */
@ActivityScope
@Subcomponent(modules = arrayOf(MyPetsModule::class))
interface MyPetsComponent {
    fun inject(activity: MyPetsActivity)
}