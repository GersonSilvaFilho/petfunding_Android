package com.gersonsilvafilho.petfunding.add_pet

import android.util.Log
import com.gersonsilvafilho.petfunding.model.Pet
import io.reactivex.Observable
import io.reactivex.functions.Function3

/**
 * Created by GersonSilva on 5/6/17.
 */
class AddPetPresenter : AddPetContract.Presenter {


    var mAddPetView : AddPetContract.View
    var mAddPetAboutView : AddPetContract.ViewAbout? = null

    constructor(addPetView: AddPetContract.View)
    {
        mAddPetView = addPetView


    }

    private var loginDetails: Observable<Int>? = null

    override fun init(aboutAddFragment: AboutAddFragment) {
        mAddPetAboutView = aboutAddFragment


        mAddPetView.saveButtonClick().withLatestFrom(
                mAddPetAboutView!!.descriptionChanges(),
                mAddPetAboutView!!.nameChanges(),
                Function3 { t0:Unit, t1:CharSequence, t2:CharSequence ->  t1.toString() + t2.toString()})
                .subscribe { a -> Log.d("RXAndroid LEGAAAAAL", "Click legal " + a.toString()) }

    }

    private var mCurrentPet: Pet = Pet()

//    private fun setPet()
//    {
//        mCurrentPet.name = mAddPetView.getPetName()
//        mCurrentPet.description = mAddPetView.getPetDescription()
//        mCurrentPet.type = mAddPetView.getPetType()
//        mCurrentPet.sex = mAddPetView.getPetSex()
//        mCurrentPet.birthDate = mAddPetView.getPetBirth()
//        mCurrentPet.size = mAddPetView.getPetSize()
//        mCurrentPet.furSize = mAddPetView.getPetFurSize()
//    }

    private data class LoginDetails(val username: String, val password: String) {
        fun isValid(): Boolean = username.isNotBlank() && password.isNotBlank()
    }
}