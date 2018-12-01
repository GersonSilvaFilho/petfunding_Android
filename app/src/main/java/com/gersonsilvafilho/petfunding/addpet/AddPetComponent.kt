package com.gersonsilvafilho.petfunding.chat

import com.gersonsilvafilho.petfunding.addpet.AddPetActivity
import com.gersonsilvafilho.petfunding.util.ActivityScope
import dagger.Subcomponent

/**
 * Created by GersonSilva on 5/20/17.
 */
@ActivityScope
@Subcomponent(modules = arrayOf(AddPetModule::class))
interface AddPetComponent {
    fun inject(activity: AddPetActivity)
}