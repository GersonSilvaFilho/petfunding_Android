package com.gersonsilvafilho.petfunding.main

import dagger.Module
import dagger.Provides


@Module
class MainMenuModule(val mainMenuView: MainMenuContract.View) {

    @Provides
    fun provideMainMenuPresenter(): MainMenuPresenter {
        return  MainMenuPresenter(mainMenuView)
    }
}