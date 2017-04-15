package com.gersonsilvafilho.petfunding.data

import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import rx.Subscriber


/**
 * Created by GersonSilva on 4/10/17.
 */
interface AuthManager {

    fun signInGoogle(acct: GoogleSignInAccount, signInSubscriber: Subscriber<String>)

    fun signOut(signOutSubscriber: Subscriber<String>)

    fun isSignedIn(): Boolean

    fun getCurrentUserId(): String
}