package com.gersonsilvafilho.petfunding.model.user

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
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
    fun getCurrentUserId():String
    fun addMatch(petId:String): Single<String>
    fun addUnmatch(petId:String): Completable
    fun getUsernameFromFacebook()
    fun getCurrentUser():User
    fun checkIfMatchExists(petId: String): Boolean
    fun monitorCurrentUser()
    fun checkIfChatExists(petId: String): String?
    fun getAllMatches():List<Match>

    fun currentUserChanged(): Observable<User>
}