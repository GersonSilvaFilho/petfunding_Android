package com.gersonsilvafilho.petfunding.splash.dagger

import com.gersonsilvafilho.petfunding.splash.ui.SplashActivity
import com.gersonsilvafilho.petfunding.util.ActivityScope
import dagger.Subcomponent


/**
 * Created by GersonSilva on 4/10/17.
 */

@ActivityScope
@Subcomponent(modules = [SplashModule::class])
interface SplashComponent {
    fun inject(activity: SplashActivity)
}