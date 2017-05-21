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
import com.gersonsilvafilho.petfunding.main.MainMenuActivity
import com.gersonsilvafilho.petfunding.splash.SplashContract.View
import com.gersonsilvafilho.petfunding.util.PetApplication
import kotlinx.android.synthetic.main.activity_splash.*
import org.jetbrains.anko.startActivity
import javax.inject.Inject

class SplashActivity : AppCompatActivity() , View{

    private var mFacebookCallback: FacebookCallback<LoginResult>? = null
    var callbackManager: CallbackManager? = null

    @Inject
    lateinit var mActionsListener: SplashPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        initDagger()

        mActionsListener.initView(this)

        initFacebook()
    }

    private fun initDagger()
    {
        (application as PetApplication).getAppComponent().inject(this)
    }

    private fun initFacebook()
    {
        callbackManager = CallbackManager.Factory.create()
        fbLoginButton.setReadPermissions("email", "public_profile")
        mFacebookCallback = object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                handleFacebookAccessToken(loginResult.accessToken)
                mActionsListener.facebookSuccess()
            }

            override fun onCancel() {
                mActionsListener.facebookCancel()
            }

            override fun onError(error: FacebookException) {
                mActionsListener.facebookOnError()
            }
        }

        fbLoginButton.registerCallback(callbackManager, mFacebookCallback)
    }

    public override fun onStart() {
        super.onStart()
    }

    public override fun onStop() {
        super.onStop()
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
        mActionsListener.firebaseSuccess(token.token)
    }

    override fun goToMainMenuActivity() {
        startActivity<MainMenuActivity>()
    }

    override fun startSelfActivity() {
        startActivity<SplashActivity>()
    }

}
