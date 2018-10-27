package com.gersonsilvafilho.petfunding.main.dagger

import com.gersonsilvafilho.petfunding.main.ui.MainMenuContract
import com.gersonsilvafilho.petfunding.main.ui.MainMenuPresenter
import com.gersonsilvafilho.petfunding.model.match.MatchReposity
import com.gersonsilvafilho.petfunding.model.pet.PetRepository
import com.gersonsilvafilho.petfunding.model.user.UserRepository
import dagger.Module
import dagger.Provides


@Module
class MainMenuModule(val chatActivity: MainMenuContract.View){

    @Provides
    fun provideMainMenuPresenter(userRepository: UserRepository, petRepository: PetRepository, matchReposity: MatchReposity): MainMenuContract.Presenter {
        return MainMenuPresenter(chatActivity, userRepository, petRepository, matchReposity)
    }
}