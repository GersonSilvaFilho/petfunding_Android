package com.gersonsilvafilho.petfunding.util

import android.app.Application
import com.gersonsilvafilho.petfunding.dagger.AppComponent
import com.gersonsilvafilho.petfunding.dagger.AppModule
import com.gersonsilvafilho.petfunding.dagger.DaggerAppComponent
import com.google.firebase.FirebaseApp
import com.miguelbcr.ui.rx_paparazzo2.RxPaparazzo







/**
 * Created by GersonSilva on 5/7/17.
 */
class PetApplication : Application() {

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        RxPaparazzo.register(this)
        FirebaseApp.initializeApp(this)
        appComponent = initDagger(this)
    }

    protected fun initDagger(application: PetApplication): AppComponent {
        return DaggerAppComponent.builder()
                .appModule(AppModule(application))
                .build()
    }

    fun getAppComponent(): AppComponent {
        return appComponent
    }

}