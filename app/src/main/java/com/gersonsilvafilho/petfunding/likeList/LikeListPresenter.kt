package com.gersonsilvafilho.petfunding.likeList

import com.gersonsilvafilho.petfunding.model.pet.Pet
import com.gersonsilvafilho.petfunding.model.pet.PetRepository
import com.gersonsilvafilho.petfunding.model.user.UserRepository

/**
 * Created by GersonSilva on 5/22/17.
 */
class LikeListPresenter : LikeListContract.Presenter {
    private val  mPetRepository:PetRepository
    private val  mUserRepository: UserRepository
    private val  mView: LikeListContract.View

    constructor(likeListView: LikeListContract.View, userRepository: UserRepository, petRepository: PetRepository)
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

    override fun petSelected(pet: Pet)
    {
        mView.startDetails(pet)
    }
}