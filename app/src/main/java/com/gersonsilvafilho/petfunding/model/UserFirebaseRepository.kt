package com.gersonsilvafilho.petfunding.model

import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import durdinapps.rxfirebase2.RxFirebaseAuth
import io.reactivex.Observable

/**
 * Created by GersonSilva on 5/8/17.
 */
class UserFirebaseRepository :UserRepository
{
    private val  mAuth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun userStatus(): Observable<Boolean> {
        return RxFirebaseAuth.observeAuthState(mAuth)
                .map { t -> t.currentUser != null }
    }

    override fun loginWithFacebook(token: String): Observable<Boolean> {
        val credential = FacebookAuthProvider.getCredential(token)
        return RxFirebaseAuth.signInWithCredential((mAuth), credential)
                .map { authResult -> authResult.user!= null }
                .toObservable()
    }


}