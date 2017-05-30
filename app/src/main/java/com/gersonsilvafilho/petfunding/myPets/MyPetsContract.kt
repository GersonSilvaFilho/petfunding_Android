package com.gersonsilvafilho.petfunding.myPets

import com.gersonsilvafilho.petfunding.model.pet.Pet

/**
 * Created by GersonSilva on 5/22/17.
 */
interface MyPetsContract {

    interface View
    {
        fun setAdapter(likedPets:List<Pet>)
        fun onPetClicked(): (Pet) -> Unit
        fun startDetails(pet: Pet)
        fun onPetEdit(): (Pet) -> Unit
        fun startEditPet(pet: Pet)
    }

    interface Presenter
    {
        fun loadLikes()
        fun petSelected(pet: Pet)
        fun  petEdit(it: Pet)
    }
}
