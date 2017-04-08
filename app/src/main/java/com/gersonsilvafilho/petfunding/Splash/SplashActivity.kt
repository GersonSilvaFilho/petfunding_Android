package com.gersonsilvafilho.petfunding.Splash

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.gersonsilvafilho.petfunding.R
import com.gersonsilvafilho.petfunding.Splash.SplashContract.View
import com.jakewharton.rxbinding2.view.clicks
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_splash.*


class SplashActivity : AppCompatActivity() , View{

    private var  mActionsListener: SplashPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        mActionsListener = SplashPresenter(this)
    }

    override fun showToast()
    {
        Toast.makeText(this, "Teste", Toast.LENGTH_LONG).show()
    }

    override fun onLoginClick() : Observable<Unit> = fbLoginButton.clicks()

    override fun showFacebookError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
