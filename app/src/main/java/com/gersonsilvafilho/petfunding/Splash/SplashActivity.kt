package com.gersonsilvafilho.petfunding.splash

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.gersonsilvafilho.petfunding.R
import com.gersonsilvafilho.petfunding.Splash.SplashPresenter
import com.gersonsilvafilho.petfunding.splash.SplashContract.View
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import durdinapps.rxfirebase2.RxFirebaseAuth
import kotlinx.android.synthetic.main.activity_splash.*
import javax.inject.Inject

class SplashActivity : AppCompatActivity() , View{

    private var mFacebookCallback: FacebookCallback<LoginResult>? = null
    private var mAuth: FirebaseAuth? = null
    var callbackManager: CallbackManager? = null
    private var mAuthListener: FirebaseAuth.AuthStateListener? = null

    @Inject
    lateinit var  mActionsListener: SplashPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        DaggerSplashComponent.builder()
                .splashModule(SplashModule(this))
                .build().inject(this)

        callbackManager = CallbackManager.Factory.create()
        fbLoginButton.setReadPermissions("email", "public_profile")
        mFacebookCallback = object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                handleFacebookAccessToken(loginResult.accessToken)
                mActionsListener.facebookSuccess(loginResult)
            }

            override fun onCancel() {
                mActionsListener.facebookCancel()
            }

            override fun onError(error: FacebookException) {
                mActionsListener.facebokkOnError(error)
            }
        }



        fbLoginButton.registerCallback(callbackManager, mFacebookCallback)

        FirebaseApp.initializeApp(this)
        mAuth = FirebaseAuth.getInstance()

        mAuthListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
            val user = firebaseAuth.currentUser
            if (user != null) {

            } else {

            }
        }
    }

    public override fun onStart() {
        super.onStart()
        (mAuth!!).addAuthStateListener(mAuthListener!!)
    }

    public override fun onStop() {
        super.onStop()
        if (mAuthListener != null) {
            (mAuth!!).removeAuthStateListener(mAuthListener!!)
        }
    }

    override fun showToast()
    {
        Toast.makeText(this, "Teste", Toast.LENGTH_LONG).show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)

        // Pass the activity result back to the Facebook SDK
        callbackManager?.onActivityResult(requestCode, resultCode, data)
    }


    override fun showFacebookError() {
        Toast.makeText(this, "Facebook Error", Toast.LENGTH_LONG).show()
    }

    override fun facebookSuccess() {
        Toast.makeText(this, "Facebook Success", Toast.LENGTH_LONG).show()
    }

    override fun showFirebaseSuccess() {
        Toast.makeText(this, "Firebase Success", Toast.LENGTH_LONG).show()
    }

    private fun handleFacebookAccessToken(token: AccessToken) {

        val credential = FacebookAuthProvider.getCredential(token.getToken())
        RxFirebaseAuth.signInWithCredential((mAuth!!), credential)
        (mAuth!!).signInWithCredential(credential).addOnCompleteListener(object: OnCompleteListener<AuthResult> {
            override fun onComplete(task: Task<AuthResult>) {
                if (!task.isSuccessful()) {
                    mActionsListener.firebaseSuccess()
                }
            }
        })
    }

}
