package com.gersonsilvafilho.petfunding.model.pet

import com.google.firebase.database.FirebaseDatabase
import durdinapps.rxfirebase2.DataSnapshotMapper
import durdinapps.rxfirebase2.RxFirebaseDatabase
import io.reactivex.Observable

/**
 * Created by GersonSilva on 5/8/17.
 */
class PetFirebaseRepository : PetRepository {

    val database = FirebaseDatabase.getInstance()
    var petsRef = database.getReference("pets")

    override fun getPets(): Observable<List<Pet>> {
        return RxFirebaseDatabase.observeSingleValueEvent(petsRef, DataSnapshotMapper.listOf(Pet::class.java)).toObservable()
    }



}