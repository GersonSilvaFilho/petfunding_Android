package com.gersonsilvafilho.petfunding.myPets

import com.gersonsilvafilho.petfunding.model.pet.Pet
import com.gersonsilvafilho.petfunding.model.pet.PetRepository
import com.gersonsilvafilho.petfunding.model.user.UserRepository

/**
 * Created by GersonSilva on 5/22/17.
 */
class MyPetsPresenter : MyPetsContract.Presenter {


    private val  mPetRepository:PetRepository
    private val  mUserRepository: UserRepository
    private val  mView: MyPetsContract.View

    constructor(likeListView: MyPetsContract.View, userRepository: UserRepository, petRepository: PetRepository)
    {
        mView = likeListView
        mUserRepository = userRepository
        mPetRepository = petRepository

        loadLikes()
    }


    override fun loadLikes() {
        mPetRepository.getPetsFromUserId(mUserRepository.getCurrentUserId()).subscribe{l ->
            mView.setAdapter(l)
        }
    }

    override fun petSelected(pet: Pet) {
        mView.startDetails(pet)
    }

    override fun petEdit(it: Pet) {
        mView.startEditPet(it)
    }

    override fun getUsersFromPet(groupOrdinal:Int, petId:String)
    {
        mUserRepository.getUserFromMatch(petId).subscribe{l ->
            mView.setUser(groupOrdinal, l)
        }
    }
}