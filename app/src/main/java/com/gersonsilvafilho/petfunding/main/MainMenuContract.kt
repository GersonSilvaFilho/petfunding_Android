package com.gersonsilvafilho.petfunding.main

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
        fun filterTypeChanges(): Observable<List<String>>
        fun applyButtonClicked(): Observable<Unit>
        fun hideFilterView()
        fun filterSexChanges(): Observable<List<String>>
        fun filterSizeChanges(): Observable<List<String>>
        fun filterConditionChanges(): Observable<List<String>>
        fun filterLikeChanges(): Observable<List<String>>
        fun filterAgeChanges(): Observable<List<String>>
        fun startSplashActivity()
    }

    interface Presenter
    {
        fun loadPets()
        fun userLogout()
        fun userMatchedPet(pet:Pet)
        fun userUnmatchedPet(pet:Pet)
        fun setUserProfile()
    }
}