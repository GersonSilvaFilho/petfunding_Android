package com.gersonsilvafilho.petfunding.splash

import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.gersonsilvafilho.library.util.network.NetworkManager
import com.gersonsilvafilho.library.view.View
import com.gersonsilvafilho.petfunding.R
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import rx.Subscriber
import javax.inject.Inject


/**
 * Created by GersonSilva on 3/21/17.
 */

class SplashPresenter @Inject constructor(val splashView: SplashContract.View, networkManager: NetworkManager) : com.gersonsilvafilho.library.presenter.BasePresenter<View>(networkManager) {



    override fun refreshData() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private var signInSubscriber: Subscriber<String>? = null

    fun facebookSuccess(loginResult: LoginResult)
    {

    }

    fun facebookCancel()
    {

    }

    fun facebokkOnError(error: FacebookException)
    {

    }
    fun firebaseSuccess()
    {
        splashView.showFirebaseSuccess()
    }

    fun signInWithFacebook(facebookSignInAccount: GoogleSignInAccount) {
        signInSubscriber = object : Subscriber<String>(splashView) {
            override fun onNext(userId: String) {
                super.onNext(userId)
                view.navigateToUsers()
                view.hideProgress()

                FirebaseMessaging.getInstance().subscribeToTopic("user_" + userId)
            }

            override fun onError(e: Throwable) {
                super.onError(e)
                view.showMessage(R.string.authentication_failed)
                view.hideProgress()
            }
        }
        authManager.signInGoogle(googleSignInAccount, signInSubscriber, createUser)
    }
}