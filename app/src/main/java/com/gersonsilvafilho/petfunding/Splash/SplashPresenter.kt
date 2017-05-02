package com.gersonsilvafilho.petfunding.splash

import android.util.Log
import com.facebook.FacebookException
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import durdinapps.rxfirebase2.RxFirebaseAuth
import io.reactivex.disposables.Disposable
import javax.inject.Singleton


/**
 * Created by GersonSilva on 3/21/17.
 */
@Singleton
class SplashPresenter  : SplashContract.Presenter  {

    var mAuth: FirebaseAuth?
    var mSplashView: SplashContract.View

    private var firebaseIsConnected:Boolean = false

    constructor(splashView: SplashContract.View, auth: FirebaseAuth)
    {
        mSplashView = splashView
        mAuth = auth

        RxFirebaseAuth.observeAuthState(mAuth!!)
                .map { t -> t.currentUser != null }
                .subscribe { logged -> run {
                    if(firebaseIsConnected != logged)
                    {
                        if(logged)
                        {
                            mSplashView.showFirebaseSuccess()
                            mSplashView.goToMainMenuActivity()
                        }
                        else
                        {
                            mSplashView.startSelfActivity()
                        }
                        firebaseIsConnected = logged
                    }
                    Log.i("Rxfirebase2", "User logged " + logged)

                } }
    }

    override fun facebookSuccess()
    {

    }

    override fun facebookCancel()
    {
        mSplashView.showFacebookError()
    }

    override fun facebookOnError(error: FacebookException)
    {
        mSplashView.showFacebookError()
    }


    private var  subscription: Disposable? = null

    override fun firebaseSuccess(credential: AuthCredential)
    {
        subscription = RxFirebaseAuth.signInWithCredential((mAuth!!), credential)
                .map { authResult -> authResult.getUser() != null }
                .subscribe{  logged -> run {
                    Log.i("Rxfirebase2", "User logged " + logged)
                    subscription!!.dispose()
                } }

    }


}