package com.gersonsilvafilho.petfunding.splash

import com.facebook.FacebookException


/**
 * Created by GersonSilva on 3/21/17.
 */
interface SplashContract {
    interface View {
        fun facebookSuccess()
        fun showFacebookError()
        fun showToast()
        fun showFirebaseSuccess()
    }

    interface Presenter {
        fun facebookSuccess(accessToken:String)
        fun facebookCancel()
        fun facebookOnError(error: FacebookException)
        fun firebaseSuccess()
    }
}