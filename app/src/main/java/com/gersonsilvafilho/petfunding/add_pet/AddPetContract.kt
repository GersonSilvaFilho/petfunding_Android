package com.gersonsilvafilho.petfunding.add_pet

import io.reactivex.Observable


/**
 * Created by GersonSilva on 5/6/17.
 */
interface AddPetContract {

    interface ViewAbout
    {
        fun nameChanges(): Observable<CharSequence>
        fun descriptionChanges(): Observable<CharSequence>
    }


    interface View
    {
        fun saveButtonClick(): Observable<Unit>
//        fun descriptionChanges(): Observable<CharSequence>
//        fun typeChanges(): Observable<CharSequence>
//        fun sexChanges(): Observable<CharSequence>
//        fun birthChanges(): Observable<Date>
//        fun sizeChanges(): Observable<CharSequence>
//        fun furSizeChanges() : Observable<CharSequence>
//        fun getPetFunColor() : Observable<List<String>>
//
//        fun isPetVaccinated() : Observable<Boolean>
//        fun isPetDewormed() : Boolean
//        fun isPetCastrated() : Boolean
//
//        fun isPetLikeChildren() : Boolean
//        fun isPetLikeOtherAnimals() : Boolean
//        fun isPetLikeElder() : Boolean
//
//        fun isPetPhisicalProblem() : Boolean
//        fun isPetBlind() : Boolean
//        fun isPetBehaviour() : Boolean
//
//        fun getPetPersonality() : List<String>
//
//        fun getPetUf(): String
//        fun getPetCity(): String
//        fun getContactName(): String
//        fun getContectPhone(): String
//        fun getPetOng(): String
    }

    interface Presenter
    {
        fun  init(aboutAddFragment: AboutAddFragment)

    }
}