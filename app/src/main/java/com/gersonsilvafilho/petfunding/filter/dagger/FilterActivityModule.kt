package com.gersonsilvafilho.petfunding.splash.dagger

import com.gersonsilvafilho.petfunding.splash.ui.FilterActivity
import com.gersonsilvafilho.petfunding.splash.ui.FilterContract
import com.gersonsilvafilho.petfunding.splash.ui.FilterPresenter
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module
class FilterActivityModule {
    @Provides
    fun provideFilterPresenter(filterActivity: FilterActivity): FilterContract.Presenter {
        return FilterPresenter(filterActivity)
    }
}

@Module
abstract class FilterActivityBuilderModule {
    @ContributesAndroidInjector(modules = [FilterActivityModule::class])
    abstract fun contributeVoucherActivity(): FilterActivity
}