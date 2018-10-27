package com.gersonsilvafilho.petfunding.splash


/**
 * Created by GersonSilva on 3/21/17.
 */
interface SplashContract {
    interface View {
        fun goToMainMenuActivity()
        fun showToast(message: String)
    }

    interface Presenter {
        fun facebookCancel()
        fun facebookOnError()
        fun facebookSuccess(token: String)
        fun onStop()
    }
}