package com.gersonsilvafilho.petfunding.Splash

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
}