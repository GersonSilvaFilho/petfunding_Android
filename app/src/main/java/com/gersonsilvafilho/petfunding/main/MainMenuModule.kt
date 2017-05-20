package com.gersonsilvafilho.petfunding.main

import dagger.Module
import dagger.Provides


@Module
class MainMenuModule{
    @Provides
    fun provideMainMenuPresenter(): MainMenuContract.Presenter {
        return  MainMenuPresenter()
    }
}