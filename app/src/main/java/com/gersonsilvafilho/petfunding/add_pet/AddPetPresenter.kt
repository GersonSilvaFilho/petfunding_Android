package com.gersonsilvafilho.petfunding.add_pet

import android.util.Log
import com.gersonsilvafilho.petfunding.model.pet.Pet
import com.gersonsilvafilho.petfunding.model.pet.PetRepository
import com.gersonsilvafilho.petfunding.model.user.UserRepository
import khronos.day
import java.io.File
import java.util.*


/**
 * Created by GersonSilva on 5/6/17.
 */
class AddPetPresenter : AddPetContract.Presenter {

    private var mCurrentPet: Pet

    private var  mUserRepository: UserRepository
    private var mPetRepository: PetRepository

    var mView : AddPetContract.View
    lateinit var mAboutView : AddPetContract.ViewAbout
    lateinit var mInfoView : AddPetContract.ViewInfo
    lateinit var mConditionView : AddPetContract.ViewCondition
    lateinit var mContactView : AddPetContract.ViewContact

    constructor(addPetView: AddPetContract.View, petRepository: PetRepository, userRepository: UserRepository, currentPet: Pet? = Pet())
    {
        mCurrentPet = currentPet!!
        mView = addPetView
        mView.saveButtonClick().subscribe { validatePet(mCurrentPet) }

        mPetRepository = petRepository
        mUserRepository = userRepository

    }

    override fun initAbout(aboutAddFragment: AddPetContract.ViewAbout) {
        mAboutView = aboutAddFragment
        mAboutView.nameChanges().subscribe { a -> mCurrentPet.name = a.toString() }
        mAboutView.descriptionChanges().subscribe { a -> mCurrentPet.description = a.toString() }
    }

    override fun initInfo(infoAddFragment: AddPetContract.ViewInfo) {
        mInfoView = infoAddFragment
        mInfoView.typeChanges().subscribe { a -> mCurrentPet.type = a.toString() }
        mInfoView.sexChanges().subscribe { a -> mCurrentPet.sex = a.toString() }
        mInfoView.ageChanges().subscribe { a -> mCurrentPet.birthDate = a }
        mInfoView.sizeChanges().subscribe { a -> mCurrentPet.size = a.toString() }
        mInfoView.furSizeChanges().subscribe { a -> mCurrentPet.furSize = a.toString() }
        mInfoView.furColorChanges().subscribe { a -> mCurrentPet.furColors = ArrayList<String>(a)  }
    }

    override fun initCondition(conditionView: AddPetContract.ViewCondition)
    {
        mConditionView = conditionView
        mConditionView.stateChanges().subscribe { a -> mCurrentPet.castrated = a.contains("Castrado")
                                                       mCurrentPet.vaccinated = a.contains("Vacinado")
                                                       mCurrentPet.dewormed = a.contains("Desverminado")}

        mConditionView.likeChanges().subscribe { a -> mCurrentPet.likeAnimals = a.contains("Crianças")
                                                       mCurrentPet.likeChildren = a.contains("Outros Animais")
                                                       mCurrentPet.likeElders = a.contains("Idosos")}

        mConditionView.specialNeedsChanges().subscribe { a -> mCurrentPet.hasLocomotionProblems = a.contains("Problema Físico")
                                                       mCurrentPet.blind = a.contains("Cego")
                                                       mCurrentPet.hasBadBehaviour = a.contains("Comportamento")}

        mConditionView.personalityChanges().subscribe { a -> mCurrentPet.behaviour = ArrayList<String>(a) }
    }

    override fun initContact(contactView: AddPetContract.ViewContact) {
        mContactView = contactView

        mContactView.ufChanges().subscribe { a -> mCurrentPet.state = a.toString() }
        mContactView.cityChanges().subscribe { a -> mCurrentPet.city = a.toString() }
        mContactView.contactNameChanges().subscribe { a -> mCurrentPet.contactName = a.toString() }
        mContactView.contactPhoneChanges().subscribe { a -> mCurrentPet.contactPhone = a.toString() }
        mContactView.ongChanges().subscribe { a -> mCurrentPet.ongName = a.toString() }

        //Set default values for user
        mCurrentPet.contactName = mUserRepository.getCurrentUser().name
        //mCurrentPet.contactPhone =

        mContactView.setUsernameInitialValue(mUserRepository.getCurrentUser().name)
    }

    private fun validatePet(pet: Pet)
    {
        Log.d("RXAndroid", "Valido!")

        if (pet.photosUrl.count() == 0)
        {
            mView.showTab(0)
            mAboutView.showInvalidPhotosMessage()
            return
        }
        else if(pet.name.length < 2)
        {
            mView.showTab(0)
            mAboutView.showInvalidName()
            return
        }
        else if (pet.description.length < 2)
        {
            mView.showTab(0)
            mAboutView.showInvalidDescription()
            return
        }
        else if(pet.type.isNullOrEmpty())
        {
            mView.showTab(1)
            mInfoView.setTypeError()
            return
        }
        else if(pet.sex.isNullOrEmpty())
        {
            mView.showTab(1)
            mInfoView.setSexError()
            return
        }
        else if(pet.birthDate > 1.day.ago)
        {
            mView.showTab(1)
            mInfoView.setAgeError()
            return
        }
        else if(pet.size.isNullOrEmpty())
        {
            mView.showTab(1)
            mInfoView.setSizeError()
            return
        }
        else if(pet.furSize.isEmpty())
        {
            mView.showTab(1)
            mInfoView.setFurSizeError()
            return
        }
        else if(pet.furColors.isEmpty())
        {
            mView.showTab(1)
            mInfoView.setFurColorError()
            return
        }
        else if(pet.contactName.isNullOrEmpty())
        {
            mView.showTab(3)
            mContactView.setContactNameError()
            return
        }
        else if(pet.contactPhone.isNullOrEmpty())
        {
            mView.showTab(3)
            mContactView.setContactPhoneError()
            return
        }



        if(pet.uid.isNullOrBlank())
        {
            pet.createdBy = mUserRepository.getCurrentUserId()
            mPetRepository.addPet(pet).doOnComplete {
                mView.showSuccessMessage()
                mView.finishActivity()
            }.doOnError {
                //Error
            }.subscribe()

        }
        else
        {
            mPetRepository.updatePet(pet).doOnComplete {
                mView.showSuccessMessage()
                mView.finishActivity()
            }.doOnError {
                //Error
            }.subscribe()
        }
    }

    override fun imageReady(num: Int, file: File) {
        mPetRepository.sendPetPhoto(num, file)
                .subscribe { a -> Log.d("RXAndroid", "Deu boa! - " + a)
            mCurrentPet.photosUrl.add(a)}
    }
}