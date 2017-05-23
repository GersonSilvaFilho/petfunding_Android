package com.gersonsilvafilho.petfunding.likeList

import com.gersonsilvafilho.petfunding.model.pet.Pet

/**
 * Created by GersonSilva on 5/22/17.
 */
interface LikeListContract {

    interface View
    {
        fun setAdapter(likedPets:List<Pet>)
        fun onPetClicked(): (Pet) -> Unit
        fun startDetails(pet: Pet)
    }

    interface Presenter
    {
        fun loadLikes()
        fun petSelected(pet: Pet)
    }
}
