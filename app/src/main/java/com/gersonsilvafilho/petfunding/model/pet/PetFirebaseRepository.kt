package com.gersonsilvafilho.petfunding.model.pet

import android.net.Uri
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import durdinapps.rxfirebase2.DataSnapshotMapper
import durdinapps.rxfirebase2.RxFirebaseDatabase
import durdinapps.rxfirebase2.RxFirebaseStorage
import io.reactivex.Completable
import io.reactivex.Observable
import java.io.File
import java.util.UUID

/**
 * Created by GersonSilva on 5/8/17.
 */
class PetFirebaseRepository : PetRepository {


    private val database = FirebaseDatabase.getInstance()
    private var petsRef = database.getReference("pets")

    override fun getPetsFromKeys(petIds: List<String>): Observable<List<Pet>> {
        return RxFirebaseDatabase.observeSingleValueEvent(petsRef, DataSnapshotMapper.listOf(Pet::class.java))
                .toObservable()
    }

    override fun getPetFromKey(petId: String): Observable<Pet> {
        val key = petsRef.child(petId)
        return RxFirebaseDatabase.observeSingleValueEvent(key, Pet::class.java).toObservable()
    }

    override fun getPets(): Observable<List<Pet>> {
        return RxFirebaseDatabase.observeSingleValueEvent(petsRef, DataSnapshotMapper.listOf(Pet::class.java)).toObservable()
    }

    override fun getPetsFromUserId(userId:String): Observable<List<Pet>> {
        val key = petsRef.orderByChild("createdBy").equalTo(userId)
        return RxFirebaseDatabase.observeSingleValueEvent(key, DataSnapshotMapper.listOf(Pet::class.java)).toObservable()
    }

    override fun addPet(pet:Pet): Completable{
        val key = petsRef.push()
        pet.uid = key.key!!
        return RxFirebaseDatabase.updateChildren(key, pet.toMap())
    }

    override fun updatePet(pet: Pet): Completable {
        val key = petsRef.child(pet.uid)
        return RxFirebaseDatabase.updateChildren(key, pet.toMap())
    }

    override fun sendPetPhoto(index: Int, file: File): Observable<String> {
        val storage = FirebaseStorage.getInstance()
        val reference = storage.getReferenceFromUrl("gs://petfunding-7ab38.appspot.com/pets")
            .child(UUID.randomUUID().toString())


        return RxFirebaseStorage.putFile(reference, Uri.fromFile(file))
//            .flatMap { task ->
//                Observable.create<String> { emmiter ->
//                    reference.downloadUrl.onSuccessTask {
//                        emmiter.onNext(it.toString())
//                    }
//                }
//            }
            .map { it.uploadSessionUri.toString() }
                .toObservable()
    }

}
