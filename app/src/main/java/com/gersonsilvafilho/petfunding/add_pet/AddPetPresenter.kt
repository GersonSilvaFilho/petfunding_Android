package com.gersonsilvafilho.petfunding.add_pet

import android.net.Uri
import android.util.Log
import com.gersonsilvafilho.petfunding.model.Pet
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import durdinapps.rxfirebase2.RxFirebaseStorage
import java.io.File
import java.util.*


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
        mInfoView!!.furColorChanges().subscribe { a -> mCurrentPet.furColors = a as ArrayList<String> }
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

        mConditionView!!.personalityChanges().subscribe { a -> mCurrentPet.behaviour = a as ArrayList<String> }
    }

    override fun initContact(contactView: AddPetContract.ViewContact) {
        mContactView = contactView

        mContactView!!.ufChanges().subscribe { a -> mCurrentPet.state = a.toString() }
        mContactView!!.cityChanges().subscribe { a -> mCurrentPet.city = a.toString() }
        mContactView!!.contactNameChanges().subscribe { a -> mCurrentPet.contactName = a.toString() }
        mContactView!!.contactPhoneChanges().subscribe { a -> mCurrentPet.contactPhone = a.toString() }
        mContactView!!.ongChanges().subscribe { a -> mCurrentPet.ongName = a.toString() }

    }

    private fun validatePet(pet:Pet)
    {
        Log.d("RXAndroid", "Valido!")

        pet.createdBy = FirebaseAuth.getInstance().currentUser!!.uid

        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("pets")
        val key = myRef.push().key
        database.getReference().child("pets").child(key).setValue(pet).addOnCompleteListener { task ->
            mView.showSuccessMessage()
            mView.finishActivity() }
    }

    override fun imageReady(num: Int, file: File) {
        val storage = FirebaseStorage.getInstance()
        RxFirebaseStorage.putFile(storage.getReferenceFromUrl("gs://petfunding-7ab38.appspot.com/pets").child(UUID.randomUUID().toString()), Uri.fromFile(file))
                         .subscribe { a -> Log.d("RXAndroid", "Deu boa! - " + a.bytesTransferred)
                                            mCurrentPet.photosUrl.add(a.downloadUrl.toString())}
    }
}