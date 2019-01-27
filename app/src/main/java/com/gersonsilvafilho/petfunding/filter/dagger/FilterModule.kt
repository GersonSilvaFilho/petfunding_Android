package com.gersonsilvafilho.petfunding.splash.dagger

import com.gersonsilvafilho.petfunding.splash.ui.FilterActivity
import com.gersonsilvafilho.petfunding.splash.ui.FilterContract
import com.gersonsilvafilho.petfunding.splash.ui.FilterPresenter
import dagger.Module
import dagger.Provides

@Module
class FilterModule(private val mFilterActivity: FilterActivity) {
    @Provides
    fun provideFilterPresenter(): FilterContract.Presenter {
        return FilterPresenter(mFilterActivity)
    }
}