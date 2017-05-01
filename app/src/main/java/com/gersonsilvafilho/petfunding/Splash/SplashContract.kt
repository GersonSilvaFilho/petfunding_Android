package com.gersonsilvafilho.petfunding.splash

import com.facebook.FacebookException
import com.facebook.login.LoginResult


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
        fun facebookSuccess(loginResult: LoginResult)
        fun facebookCancel()
        fun facebokkOnError(error: FacebookException)
        fun firebaseSuccess()
    }
}