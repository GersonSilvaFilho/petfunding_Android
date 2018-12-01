package com.gersonsilvafilho.petfunding.mypets

import com.gersonsilvafilho.petfunding.model.match.MatchReposity
import com.gersonsilvafilho.petfunding.model.pet.PetRepository
import com.gersonsilvafilho.petfunding.model.user.UserRepository
import dagger.Module
import dagger.Provides

/**
 * Created by GersonSilva on 5/22/17.
 */
@Module
class MyPetsModule (val likeListContract: MyPetsContract.View){
    @Provides
    fun provideMyPetsPresenter(userRepository: UserRepository, petRepository: PetRepository, matchReposity: MatchReposity): MyPetsContract.Presenter{
        return MyPetsPresenter(likeListContract,userRepository, petRepository, matchReposity)
    }
}