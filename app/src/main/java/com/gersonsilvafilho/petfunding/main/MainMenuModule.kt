package com.gersonsilvafilho.petfunding.main

import com.gersonsilvafilho.petfunding.chat.ChatActivity
import com.gersonsilvafilho.petfunding.model.pet.PetRepository
import com.gersonsilvafilho.petfunding.model.user.UserRepository
import dagger.Module
import dagger.Provides


@Module
class MainMenuModule(val chatActivity: MainMenuContract.View){

    @Provides
    fun provideMainMenuPresenter(userRepository: UserRepository, petRepository: PetRepository): MainMenuContract.Presenter {
        return  MainMenuPresenter(chatActivity, userRepository, petRepository)
    }
}