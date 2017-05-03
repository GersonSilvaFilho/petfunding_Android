package com.gersonsilvafilho.petfunding.splash

import com.facebook.FacebookException
import com.google.firebase.auth.AuthCredential


/**
 * Created by GersonSilva on 3/21/17.
 */
interface SplashContract {
    interface View {
        fun facebookSuccess()
        fun showFacebookError()
        fun showToast()
        fun showFirebaseSuccess()
        fun goToMainMenuActivity()
        fun startSelfActivity()
    }

    interface Presenter {
        fun facebookSuccess()
        fun facebookCancel()
        fun facebookOnError(error: FacebookException)
        fun firebaseSuccess(credentials: AuthCredential)
    }
}