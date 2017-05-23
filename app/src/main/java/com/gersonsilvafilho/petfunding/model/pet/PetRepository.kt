package com.gersonsilvafilho.petfunding.model.pet

import io.reactivex.Completable
import io.reactivex.Observable
import java.io.File


/**
 * Created by GersonSilva on 5/8/17.
 */
interface PetRepository {
    fun getPets(): Observable<List<Pet>>
    fun addPet(pet:Pet): Completable
    fun sendPetPhoto(index:Int, file:File): Observable<String>
    fun getPetFromKey(petId:String): Observable<Pet>
    fun getPetsFromKeys(petIds:List<String>): Observable<List<Pet>>
}