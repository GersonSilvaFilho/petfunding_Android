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
        mPetRepository.getPets().subscribe{l ->
            mView.setAdapter(l.filter {
                p -> mUserRepository.getAllMatches().map { m-> m.petId }.contains(p.uid)})
        }
    }

    override fun petSelected(pet: Pet) {
        mView.startDetails(pet)
    }

    override fun petEdit(it: Pet) {
        mView.startEditPet(it)
    }
}