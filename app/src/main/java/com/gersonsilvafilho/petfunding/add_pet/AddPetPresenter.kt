package com.gersonsilvafilho.petfunding.add_pet

import android.util.Log
import com.gersonsilvafilho.petfunding.model.Pet

/**
 * Created by GersonSilva on 5/6/17.
 */
class AddPetPresenter : AddPetContract.Presenter {

    private var mCurrentPet: Pet = Pet()

    var mView : AddPetContract.View
    var mAboutView : AddPetContract.ViewAbout? = null
    var mInfoView : AddPetContract.ViewInfo? = null
    var mConditionView : AddPetContract.ViewCondition? = null
    var mContactView : AddPetContract.ViewContact? = null


    constructor(addPetView: AddPetContract.View)
    {
        mView = addPetView

        mView.saveButtonClick().subscribe { validatePet(mCurrentPet) }
    }

    override fun initAbout(aboutAddFragment: AddPetContract.ViewAbout) {
        mAboutView = aboutAddFragment
        mAboutView!!.nameChanges().subscribe { a -> mCurrentPet.name = a.toString() }
        mAboutView!!.descriptionChanges().subscribe { a -> mCurrentPet.description = a.toString() }
    }

    override fun initInfo(infoAddFragment: AddPetContract.ViewInfo) {
        mInfoView = infoAddFragment
        mInfoView!!.typeChanges().subscribe { a -> mCurrentPet.type = a.toString() }
        mInfoView!!.sexChanges().subscribe { a -> mCurrentPet.sex = a.toString() }
        //mInfoView!!.ageChanges().subscribe { a -> mCurrentPet.birthDate = a.toString() }
        mInfoView!!.sizeChanges().subscribe { a -> mCurrentPet.size = a.toString() }
        mInfoView!!.furSizeChanges().subscribe { a -> mCurrentPet.furSize = a.toString() }
        mInfoView!!.furColorChanges().subscribe { a -> mCurrentPet.furColors = a }
    }

    override fun initCondition(conditionView: AddPetContract.ViewCondition)
    {
        mConditionView = conditionView
        mConditionView!!.stateChanges().subscribe { a -> mCurrentPet.isCastrated = a.contains("Castrado")
                                                         mCurrentPet.isVaccinated = a.contains("Vacinado")
                                                         mCurrentPet.isDewormed = a.contains("Desverminado")}

        mConditionView!!.likeChanges().subscribe { a -> mCurrentPet.likeAnimals = a.contains("Crianças")
                                                         mCurrentPet.likeChildren = a.contains("Outros Animais")
                                                         mCurrentPet.likeElders = a.contains("Idosos")}

        mConditionView!!.specialNeedsChanges().subscribe { a -> mCurrentPet.hasLocomotionProblems = a.contains("Problema Físico")
                                                         mCurrentPet.isBlind = a.contains("Cego")
                                                         mCurrentPet.hasBadBehaviour = a.contains("Comportamento")}

        mConditionView!!.personalityChanges().subscribe { a -> mCurrentPet.behaviour = a}
    }

    override fun initContact(contactView: AddPetContract.ViewContact) {
        mContactView = contactView

        mContactView!!.ufChanges().subscribe { a -> mCurrentPet.state = a.toString() }
        mContactView!!.cityChanges().subscribe { a -> mCurrentPet.city = a.toString() }
        mContactView!!.contactNameChanges().subscribe { a -> mCurrentPet.contactName = a.toString() }
        mContactView!!.contactPhoneChanges().subscribe { a -> mCurrentPet.contactPhone = a.toString() }
        mContactView!!.ongChanges().subscribe { a -> mCurrentPet.ongName = a.toString() }

    }


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

    private fun validatePet(pet:Pet)
    {
        Log.d("RXAndroid", "Valido!")
    }


    private data class LoginDetails(val username: String, val password: String) {
        fun isValid(): Boolean = username.isNotBlank() && password.isNotBlank()
    }
}