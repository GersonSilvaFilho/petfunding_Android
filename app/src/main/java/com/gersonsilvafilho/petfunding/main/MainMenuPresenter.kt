package com.gersonsilvafilho.petfunding.main

import com.gersonsilvafilho.petfunding.model.pet.Pet
import com.gersonsilvafilho.petfunding.model.pet.PetRepository
import com.gersonsilvafilho.petfunding.model.user.UserRepository


/**
 * Created by GersonSilva on 3/21/17.
 */
class MainMenuPresenter: MainMenuContract.Presenter
{
    var mMainMenuView: MainMenuContract.View

    var mUserRepository: UserRepository

    var mPetRepository: PetRepository

    constructor(view: MainMenuContract.View, userRepository: UserRepository, petRepository: PetRepository) {
        mMainMenuView = view
        mUserRepository = userRepository
        mPetRepository = petRepository
    }


    override fun userMatchedPet(pet: Pet) {
        if(mUserRepository.checkIfMatchExists(pet.uid))
        {
            mMainMenuView.showItsMatchDialog(pet)

        }
        else
        {
            mUserRepository.addMatch(pet.uid).toObservable().subscribe {a -> mMainMenuView.showItsMatchDialog(pet)}
        }

    }

    override fun userLogout() {
        mUserRepository.userLogout()
    }

    override fun loadPets() {
        mPetRepository.getPets().subscribe { p ->  mMainMenuView.updateCardAdapter(p)}
    }

}