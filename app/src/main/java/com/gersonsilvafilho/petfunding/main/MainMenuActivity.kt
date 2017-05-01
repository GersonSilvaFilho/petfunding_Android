package com.gersonsilvafilho.petfunding.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.gersonsilvafilho.petfunding.R
import javax.inject.Inject

class MainMenuActivity : AppCompatActivity(), MainMenuContract.View {

    @Inject
    lateinit var  mActionsListener: MainMenuPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        DaggerMainMenuComponent.builder()
                .mainMenuModule(MainMenuModule(this))
                .build().inject(this)

    }
}
