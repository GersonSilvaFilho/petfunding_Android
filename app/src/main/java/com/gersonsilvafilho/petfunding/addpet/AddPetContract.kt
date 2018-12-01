package com.gersonsilvafilho.petfunding.addpet

import com.gersonsilvafilho.petfunding.model.pet.Pet
import io.reactivex.Observable
import java.io.File
import java.util.*


/**
 * Created by GersonSilva on 5/6/17.
 */
interface AddPetContract {

    interface ViewAbout
    {
        fun nameChanges(): Observable<CharSequence>
        fun descriptionChanges(): Observable<CharSequence>

        fun showInvalidName()
        fun showInvalidDescription()
        fun showInvalidPhotosMessage()
    }


    interface ViewInfo
    {
        fun typeChanges(): Observable<CharSequence>
        fun sexChanges(): Observable<CharSequence>
        fun ageChanges(): Observable<Date>
        fun sizeChanges(): Observable<CharSequence>
        fun furSizeChanges(): Observable<CharSequence>
        fun furColorChanges(): Observable<List<String>>
        fun setTypeError()
        fun setFurColorError()
        fun setSizeError()
        fun setFurSizeError()
        fun setAgeError()
        fun setSexError()
    }

    interface ViewCondition
    {
        fun stateChanges(): Observable<List<String>>
        fun likeChanges(): Observable<List<String>>
        fun specialNeedsChanges(): Observable<List<String>>
        fun personalityChanges(): Observable<List<String>>
    }

    interface ViewContact
    {
        fun ufChanges(): Observable<CharSequence>
        fun cityChanges(): Observable<CharSequence>
        fun contactNameChanges(): Observable<CharSequence>
        fun contactPhoneChanges(): Observable<CharSequence>
        fun ongChanges(): Observable<CharSequence>
        fun setContactNameError()
        fun setContactPhoneError()
        fun setUsernameInitialValue(username: String)
        fun setUserContactInitialValue(phone: String)
    }

    interface View
    {
        fun saveButtonClick(): Observable<Unit>
        fun finishActivity()
        fun showSuccessMessage()
        fun showTab(tabId:Int)
    }

    interface Presenter
    {
        fun  initAbout(aboutAddFragment: AddPetContract.ViewAbout)
        fun  initInfo(infoAddFragment: AddPetContract.ViewInfo)
        fun  initCondition(conditionView: AddPetContract.ViewCondition)
        fun  initContact(contactView: AddPetContract.ViewContact, pet: Pet?)

        fun  imageReady(num:Int, file: File)
    }
}