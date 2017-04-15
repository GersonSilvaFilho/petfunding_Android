package com.gersonsilvafilho.petfunding.splash

import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.gersonsilvafilho.library.util.network.NetworkManager
import com.gersonsilvafilho.library.view.DefaultSubscriber
import com.gersonsilvafilho.petfunding.R
import com.gersonsilvafilho.petfunding.data.AuthManager
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import javax.inject.Inject


/**
 * Created by GersonSilva on 3/21/17.
 */

class SplashPresenter @Inject constructor(val splashView: SplashContract.View, networkManager: NetworkManager, private val authManager: AuthManager) : com.gersonsilvafilho.library.presenter.BasePresenter<SplashContract>(networkManager) {


    private var signInSubscriber: DefaultSubscriber<String>? = null

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
        signInSubscriber = object : DefaultSubscriber<String>(view) {
            override fun onNext(userId: String) {
                super.onNext(userId)
                //view!!.navigateToUsers()
                view!!.hideProgress()

                //FirebaseMessaging.getInstance().subscribeToTopic("user_" + userId)
            }

            override fun onError(e: Throwable) {
                super.onError(e)
                view!!.showMessage(R.string.authentication_failed)
                view!!.hideProgress()
            }
        }
        authManager.signInGoogle(facebookSignInAccount, signInSubscriber!!)
    }

    override fun refreshData(): () -> Unit {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}