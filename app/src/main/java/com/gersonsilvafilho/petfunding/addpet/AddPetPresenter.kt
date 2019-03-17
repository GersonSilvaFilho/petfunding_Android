package com.gersonsilvafilho.petfunding.addpet

import android.util.Log
import com.gersonsilvafilho.petfunding.model.pet.Pet
import com.gersonsilvafilho.petfunding.model.pet.PetRepository
import com.gersonsilvafilho.petfunding.model.user.UserRepository
import khronos.day
import java.io.File
import java.util.ArrayList


/**
 * Created by GersonSilva on 5/6/17.
 */
class AddPetPresenter(val view: AddPetContract.View, val petRepository: PetRepository, val userRepository: UserRepository) : AddPetContract.Presenter {

    lateinit var mAboutView : AddPetContract.ViewAbout
    lateinit var mInfoView : AddPetContract.ViewInfo
    lateinit var mConditionView : AddPetContract.ViewCondition
    lateinit var mContactView : AddPetContract.ViewContact

    private val currentPet: Pet = Pet()

    override fun onCreate() {
        view.saveButtonClick().subscribe { validatePet(currentPet) }
    }

    override fun initAbout(aboutAddFragment: AddPetContract.ViewAbout) {
        mAboutView = aboutAddFragment
        mAboutView.nameChanges().subscribe { a -> currentPet.name = a.toString() }
        mAboutView.descriptionChanges().subscribe { a -> currentPet.description = a.toString() }
    }

    override fun initInfo(infoAddFragment: AddPetContract.ViewInfo) {
        mInfoView = infoAddFragment
        mInfoView.typeChanges().subscribe { a -> currentPet.type = a.toString() }
        mInfoView.sexChanges().subscribe { a -> currentPet.sex = a.toString() }
        mInfoView.ageChanges().subscribe { a -> currentPet.birthDate = a }
        mInfoView.sizeChanges().subscribe { a -> currentPet.size = a.toString() }
        mInfoView.furSizeChanges().subscribe { a -> currentPet.furSize = a.toString() }
        mInfoView.furColorChanges().subscribe { a -> currentPet.furColors = ArrayList<String>(a) }
    }

    override fun initCondition(conditionView: AddPetContract.ViewCondition)
    {
        mConditionView = conditionView
        mConditionView.stateChanges().subscribe { a ->
            currentPet.castrated = a.contains("Castrado")
            currentPet.vaccinated = a.contains("Vacinado")
            currentPet.dewormed = a.contains("Desverminado")
        }

        mConditionView.likeChanges().subscribe { a ->
            currentPet.likeAnimals = a.contains("Crianças")
            currentPet.likeChildren = a.contains("Outros Animais")
            currentPet.likeElders = a.contains("Idosos")
        }

        mConditionView.specialNeedsChanges().subscribe { a ->
            currentPet.hasLocomotionProblems = a.contains("Problema Físico")
            currentPet.blind = a.contains("Cego")
            currentPet.hasBadBehaviour = a.contains("Comportamento")
        }

        mConditionView.personalityChanges().subscribe { a -> currentPet.behaviour = ArrayList<String>(a) }
    }

    override fun initContact(contactView: AddPetContract.ViewContact, pet:Pet?) {
        mContactView = contactView
        //If pet already exists
        if(pet != null) {
            currentPet.contactName = pet.contactName
            currentPet.contactPhone = pet.contactPhone
        }
        else {//If is creating a new pet
            currentPet.contactName = userRepository.getCurrentUser().name
        }

        mContactView.setUsernameInitialValue(currentPet.contactName)
        mContactView.setUserContactInitialValue(currentPet.contactPhone)


        mContactView.ufChanges().subscribe { a -> currentPet.state = a.toString() }
        mContactView.cityChanges().subscribe { a -> currentPet.city = a.toString() }
        mContactView.contactNameChanges().subscribe { a -> currentPet.contactName = a.toString() }
        mContactView.contactPhoneChanges().subscribe { a -> currentPet.contactPhone = a.toString() }
        mContactView.ongChanges().subscribe { a -> currentPet.ongName = a.toString() }



    }

    private fun validatePet(pet: Pet)
    {
        Log.d("RXAndroid", "Valido!")

        if (pet.photosUrl.count() == 0)
        {
            view.showTab(0)
            mAboutView.showInvalidPhotosMessage()
            return
        }
        else if(pet.name.length < 2)
        {
            view.showTab(0)
            mAboutView.showInvalidName()
            return
        }
        else if (pet.description.length < 2)
        {
            view.showTab(0)
            mAboutView.showInvalidDescription()
            return
        }
        else if(pet.type.isNullOrEmpty())
        {
            view.showTab(1)
            mInfoView.setTypeError()
            return
        }
        else if(pet.sex.isNullOrEmpty())
        {
            view.showTab(1)
            mInfoView.setSexError()
            return
        }
        else if(pet.birthDate > 1.day.ago)
        {
            view.showTab(1)
            mInfoView.setAgeError()
            return
        }
        else if(pet.size.isNullOrEmpty())
        {
            view.showTab(1)
            mInfoView.setSizeError()
            return
        }
        else if(pet.furSize.isEmpty())
        {
            view.showTab(1)
            mInfoView.setFurSizeError()
            return
        }
        else if(pet.furColors.isEmpty())
        {
            view.showTab(1)
            mInfoView.setFurColorError()
            return
        }
        else if(pet.contactName.isNullOrEmpty())
        {
            view.showTab(3)
            mContactView.setContactNameError()
            return
        }
        else if(pet.contactPhone.isNullOrEmpty())
        {
            view.showTab(3)
            mContactView.setContactPhoneError()
            return
        }



        if (userRepository.getCurrentUserId() != null && pet.uid.isNullOrBlank())
        {
            pet.createdBy = userRepository.getCurrentUserId()!!
            petRepository.addPet(pet).doOnComplete {
                view.showSuccessMessage()
                view.finishActivity()
            }.doOnError {
                //Error
            }.subscribe()

        }
        else
        {
            petRepository.updatePet(pet).doOnComplete {
                view.showSuccessMessage()
                view.finishActivity()
            }.doOnError {
                //Error
            }.subscribe()
        }
    }

    override fun imageReady(num: Int, file: File) {
        petRepository.sendPetPhoto(num, file)
                .subscribe { a -> Log.d("RXAndroid", "Deu boa! - " + a)
                    currentPet.photosUrl.add(a)
                }
    }
}