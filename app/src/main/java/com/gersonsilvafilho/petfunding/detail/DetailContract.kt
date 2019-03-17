package com.gersonsilvafilho.petfunding.detail

import com.gersonsilvafilho.petfunding.model.pet.Pet

/**
 * Created by GersonSilva on 5/9/17.
 */
interface DetailContract {

    interface View
    {
        fun startChatActivity(pet: Pet, userId: String)
        fun startSplashActivity()
    }

    interface Presenter
    {
        fun onCreate(pet: Pet)
        fun onFloatingButtonClicked()
    }
}