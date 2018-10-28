package com.gersonsilvafilho.petfunding.main.ui

import com.gersonsilvafilho.petfunding.model.pet.Pet
import com.gersonsilvafilho.petfunding.model.user.User
import io.reactivex.Observable


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
        fun applyButtonClicked(): Observable<Unit>
        fun hideFilterView()
        fun startSplashActivity()
        fun filterTypeList(): List<String>
        fun filterSexList(): List<String>
        fun filterSizeList(): List<String>
        fun filterConditionList(): List<String>
        fun filterLikeList(): List<String>
        fun filterAgeList(): List<String>
    }

    interface Presenter
    {
        fun loadPets()
        fun userLogout()
        fun userMatchedPet(pet:Pet)
        fun userUnmatchedPet(pet:Pet)
        fun setUserProfile()
        fun onStop()
    }
}