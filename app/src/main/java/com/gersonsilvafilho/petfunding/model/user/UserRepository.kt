package com.gersonsilvafilho.petfunding.model.user

import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Singleton


/**
 * Created by GersonSilva on 5/8/17.
 */
@Singleton
interface UserRepository
{
    fun loginWithFacebook(token: String): Observable<Boolean>
    fun userStatus(): Observable<Boolean>
    fun userLogout()
    fun getCurrentUserId(): String?
    fun addUnmatch(petId:String): Completable
    fun getUsernameFromFacebook()
    fun getCurrentUser():User
    fun getUserFromMatch(matchId: String): Observable<List<User>>
    fun addMatchToUser(matchId: String): Completable
}