package com.gersonsilvafilho.petfunding.mypets

import com.gersonsilvafilho.petfunding.model.pet.Pet
import com.gersonsilvafilho.petfunding.model.user.User

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
        fun setUser(groupOrdinal: Int, user: List<User>)
    }

    interface Presenter
    {
        fun loadLikes()
        fun petSelected(pet: Pet)
        fun  petEdit(it: Pet)
        fun getUsersFromPet(groupOrdinal: Int,petId: String)
    }
}
