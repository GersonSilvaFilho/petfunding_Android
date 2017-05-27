package com.gersonsilvafilho.petfunding.add_pet

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
        fun  initContact(contactView: AddPetContract.ViewContact)

        fun  imageReady(num:Int, file: File)
    }
}