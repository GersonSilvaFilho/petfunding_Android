package com.gersonsilvafilho.petfunding.model.pet

import io.reactivex.Observable


/**
 * Created by GersonSilva on 5/8/17.
 */
interface PetRepository {
    fun getPets(): Observable<List<Pet>>
}