package com.gersonsilvafilho.petfunding.main

import com.gersonsilvafilho.petfunding.model.pet.PetFirebaseRepository
import com.gersonsilvafilho.petfunding.model.user.UserFirebaseRepository
import dagger.Module
import dagger.Provides


@Module
class MainMenuModule(val mainMenuView: MainMenuContract.View) {

    @Provides
    fun provideMainMenuPresenter(): MainMenuPresenter {
        return  MainMenuPresenter(PetFirebaseRepository(), UserFirebaseRepository(), mainMenuView)
    }
}