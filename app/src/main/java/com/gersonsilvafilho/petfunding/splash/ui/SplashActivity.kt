package com.gersonsilvafilho.petfunding.splash.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.gersonsilvafilho.petfunding.R
import com.gersonsilvafilho.petfunding.main.ui.MainMenuActivity
import com.gersonsilvafilho.petfunding.splash.ui.SplashContract.View
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_splash.fbLoginButton
import org.jetbrains.anko.startActivity
import javax.inject.Inject


class SplashActivity : AppCompatActivity(), View {

    private var mFacebookCallback: FacebookCallback<LoginResult> = object : FacebookCallback<LoginResult> {
        override fun onSuccess(loginResult: LoginResult) {
            presenter.facebookSuccess(loginResult.accessToken.token)
        }

        override fun onCancel() {
            presenter.facebookCancel()
        }

        override fun onError(error: FacebookException) {
            presenter.facebookOnError()
        }
    }

    private var callbackManager: CallbackManager = CallbackManager.Factory.create()

    @Inject
    lateinit var presenter: SplashPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        initFacebook()
    }

    private fun initFacebook()
    {
        fbLoginButton.setReadPermissions("email", "public_profile")
        fbLoginButton.registerCallback(callbackManager, mFacebookCallback)
    }

    override fun onStop() {
        presenter.onStop()
        super.onStop()
    }

    override fun showToast(resourceId: Int) = Toast.makeText(this, resourceId, Toast.LENGTH_LONG).show()


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)

        // Pass the activity result back to the Facebook SDK
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }

    override fun goToMainMenuActivity() = startActivity<MainMenuActivity>()
}
