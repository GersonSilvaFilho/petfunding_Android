package com.gersonsilvafilho.petfunding.splash

import com.gersonsilvafilho.library.view.View

/**
 * Created by GersonSilva on 3/21/17.
 */
interface SplashContract : View {
    interface View {
        fun facebookSuccess()
        fun showFacebookError()
        fun showToast()
        fun showFirebaseSuccess()
    }
}