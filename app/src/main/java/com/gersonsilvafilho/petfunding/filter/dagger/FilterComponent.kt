package com.gersonsilvafilho.petfunding.splash.dagger

import com.gersonsilvafilho.petfunding.splash.ui.FilterActivity
import com.gersonsilvafilho.petfunding.util.ActivityScope
import dagger.Subcomponent


/**
 * Created by GersonSilva on 4/10/17.
 */

@ActivityScope
@Subcomponent(modules = [FilterModule::class])
interface FilterComponent {
    fun inject(activity: FilterActivity)
}