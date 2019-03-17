package com.gersonsilvafilho.petfunding.likelist

import com.gersonsilvafilho.petfunding.model.match.MatchReposity
import com.gersonsilvafilho.petfunding.model.pet.Pet
import com.gersonsilvafilho.petfunding.model.pet.PetRepository
import com.gersonsilvafilho.petfunding.model.user.UserRepository

/**
 * Created by GersonSilva on 5/22/17.
 */
class LikeListPresenter(
    private val view: LikeListContract.View,
    private val userRepository: UserRepository,
    private val petRepository: PetRepository,
    private val matchRepository: MatchReposity
) : LikeListContract.Presenter {

    init {
        loadLikes()
    }


    override fun loadLikes() {
        userRepository.getCurrentUserId()?.let {
            matchRepository.getAllMatches(it).subscribe { t1 ->
            petRepository.getPets().subscribe { l ->
                view.setAdapter(l.filter { pet -> t1.map { match -> match.petId }.contains(pet.uid) })
            }
        }
    }
    }

    override fun petSelected(pet: Pet) {
        view.startDetails(pet)
    }
}