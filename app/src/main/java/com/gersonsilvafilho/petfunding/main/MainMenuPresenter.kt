package com.gersonsilvafilho.petfunding.main

import com.gersonsilvafilho.petfunding.model.Pet
import com.google.firebase.database.FirebaseDatabase
import durdinapps.rxfirebase2.DataSnapshotMapper
import durdinapps.rxfirebase2.RxFirebaseDatabase










/**
 * Created by GersonSilva on 3/21/17.
 */
class MainMenuPresenter constructor(var mMainMenuView: MainMenuContract.View) : MainMenuContract.Presenter  {
    override fun loadPets() {
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("pets")
        RxFirebaseDatabase.observeSingleValueEvent(myRef, DataSnapshotMapper.listOf(Pet::class.java))
                                    .subscribe { p ->  mMainMenuView.updateCardAdapter(p)}
    }

}