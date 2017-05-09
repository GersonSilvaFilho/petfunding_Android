package com.gersonsilvafilho.petfunding.model

import io.reactivex.Observable


/**
 * Created by GersonSilva on 5/8/17.
 */
interface UserRepository
{
    fun loginWithFacebook(token: String): Observable<Boolean>
    fun userStatus(): Observable<Boolean>
}