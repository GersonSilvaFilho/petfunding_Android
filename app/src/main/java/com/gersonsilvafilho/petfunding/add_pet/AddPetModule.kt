package com.gersonsilvafilho.petfunding.chat

import com.gersonsilvafilho.petfunding.add_pet.AddPetActivity
import com.gersonsilvafilho.petfunding.add_pet.AddPetContract
import com.gersonsilvafilho.petfunding.add_pet.AddPetPresenter
import com.gersonsilvafilho.petfunding.model.pet.Pet
import com.gersonsilvafilho.petfunding.model.pet.PetRepository
import com.gersonsilvafilho.petfunding.model.user.UserRepository
import dagger.Module
import dagger.Provides

/**
 * Created by GersonSilva on 5/20/17.
 */
@Module
class AddPetModule(val addPetActivity: AddPetActivity, val pet: Pet?) {
    @Provides
    fun provideMainChatPresenter(userRepository: UserRepository, petRepository: PetRepository): AddPetContract.Presenter{
        return AddPetPresenter(addPetActivity, petRepository, userRepository, pet)
    }
}