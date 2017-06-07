package com.gersonsilvafilho.petfunding.splash

import com.facebook.AccessToken
import com.gersonsilvafilho.petfunding.model.user.UserRepository


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
        fun handleFacebookAccessToken(token: AccessToken)
    }

    interface Presenter {
        fun facebookSuccess()
        fun facebookCancel()
        fun facebookOnError()
        fun firebaseSuccess(token: String)
    }
}