package com.gersonsilvafilho.petfunding.splash.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.facebook.CallbackManager
import com.gersonsilvafilho.petfunding.R
import com.gersonsilvafilho.petfunding.splash.dagger.FilterModule
import com.gersonsilvafilho.petfunding.util.PetApplication
import javax.inject.Inject


class FilterActivity : AppCompatActivity(), FilterContract.View {

    private var callbackManager: CallbackManager = CallbackManager.Factory.create()

    @Inject
    lateinit var presenter: FilterContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        setupActivityComponent()
    }

    private fun setupActivityComponent() {
        (application as PetApplication).get(this)
            .getAppComponent()
            .plus(FilterModule(this))
            .inject(this)
    }
}
