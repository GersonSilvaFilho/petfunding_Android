package com.gersonsilvafilho.petfunding.myPets

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
    fun provideMyPetsPresenter(userRepository: UserRepository, petRepository: PetRepository): MyPetsContract.Presenter{
        return MyPetsPresenter(likeListContract,userRepository, petRepository)
    }
}