package com.gersonsilvafilho.petfunding.Splash

import io.reactivex.Observable

/**
 * Created by GersonSilva on 3/21/17.
 */
interface SplashContract {
    interface View {
        fun showFacebookError();
        fun onLoginClick(): Observable<Unit>
        fun showToast()

    }
}