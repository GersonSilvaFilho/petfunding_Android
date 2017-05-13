package com.gersonsilvafilho.petfunding.model.user

import io.reactivex.Completable
import io.reactivex.Observable


/**
 * Created by GersonSilva on 5/8/17.
 */
interface UserRepository
{
    fun loginWithFacebook(token: String): Observable<Boolean>
    fun userStatus(): Observable<Boolean>
    fun userLogout()
    fun getCurrentUserId():String
    fun addMatch(petId:String): Observable<String>?
    fun getUsernameFromFacebook()
    fun getCurrentUser():User
}