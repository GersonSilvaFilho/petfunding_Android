package com.gersonsilvafilho.petfunding.detail

import com.gersonsilvafilho.petfunding.model.pet.Pet
import com.gersonsilvafilho.petfunding.model.user.UserRepository

/**
 * Created by GersonSilva on 5/9/17.
 */
class DetailPresenter(val view: DetailContract.View, val userRepository: UserRepository) : DetailContract.Presenter {

    private lateinit var pet: Pet

    override fun onCreate(pet: Pet) {
        this.pet = pet
    }

    override fun onFloatingButtonClicked() {
        if (userRepository.getCurrentUserId() != null) {
            view.startChatActivity(pet, userRepository.getCurrentUserId()!!)
        } else {
            view.startSplashActivity()
        }
    }
}