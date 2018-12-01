package com.gersonsilvafilho.petfunding.likelist

import com.gersonsilvafilho.petfunding.model.match.MatchReposity
import com.gersonsilvafilho.petfunding.model.pet.Pet
import com.gersonsilvafilho.petfunding.model.pet.PetRepository
import com.gersonsilvafilho.petfunding.model.user.UserRepository

/**
 * Created by GersonSilva on 5/22/17.
 */
class LikeListPresenter : LikeListContract.Presenter {
    private val mPetRepository: PetRepository
    private val mUserRepository: UserRepository
    private val mMatchRepository: MatchReposity
    private val mView: LikeListContract.View

    constructor(likeListView: LikeListContract.View, userRepository: UserRepository, petRepository: PetRepository, matchReposity: MatchReposity) {
        mView = likeListView
        mUserRepository = userRepository
        mPetRepository = petRepository
        mMatchRepository = matchReposity
        loadLikes()
    }


    override fun loadLikes() {
        mMatchRepository.getAllMatches(mUserRepository.getCurrentUserId()).subscribe { t1 ->
            mPetRepository.getPets().subscribe { l ->
                mView.setAdapter(l.filter { pet -> t1.map { match -> match.petId }.contains(pet.uid) })
            }
        }
    }

    override fun petSelected(pet: Pet) {
        mView.startDetails(pet)
    }
}