package com.gersonsilvafilho.petfunding.splash.ui


/**
 * Created by GersonSilva on 3/21/17.
 */
interface SplashContract {
    interface View {
        fun goToMainMenuActivity()
        fun showToast(resourceId: Int)
    }

    interface Presenter {
        fun facebookCancel()
        fun facebookOnError()
        fun facebookSuccess(token: String)
        fun onStop()
    }
}