package com.gersonsilvafilho.petfunding.main.ui

import com.gersonsilvafilho.petfunding.filter.model.FilterList
import com.gersonsilvafilho.petfunding.model.pet.Pet
import com.gersonsilvafilho.petfunding.model.user.User


/**
 * Created by GersonSilva on 3/21/17.
 */
interface MainMenuContract {
    interface View
    {
        fun updateCardAdapter(pets:List<Pet>)
        fun startDetailActivity(pet:Pet)
        fun showItsMatchDialog(pet:Pet)
        fun setDrawerUserInformation(user: User)
        fun hideRippleWaiting()
        fun showRippleWaiting()
        fun startSplashActivity()

    }

    interface Presenter
    {
        fun userLogout()
        fun userMatchedPet(pet:Pet)
        fun userUnmatchedPet(pet:Pet)
        fun setUserProfile()
        fun onStop()
        fun loadPets(filterList: FilterList)
        val filterList: FilterList
    }
}