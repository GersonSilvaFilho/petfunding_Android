package com.gersonsilvafilho.petfunding.util

import android.app.Activity
import android.app.Application
import android.content.Context
import com.gersonsilvafilho.petfunding.dagger.AppComponent
import com.gersonsilvafilho.petfunding.dagger.DaggerAppComponent
import com.google.firebase.FirebaseApp
import com.miguelbcr.ui.rx_paparazzo2.RxPaparazzo
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject


/**
 * Created by GersonSilva on 5/7/17.
 */
class PetApplication : Application(), HasActivityInjector {

    override fun activityInjector() = dispatchingActivityInjector

    @Inject
    lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Activity>

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        RxPaparazzo.register(this)
        FirebaseApp.initializeApp(this)
        initDagger()
    }

    fun get(context: Context): PetApplication {
        return context.getApplicationContext() as PetApplication
    }

    private fun initDagger() {
        appComponent = DaggerAppComponent.builder()
            .application(this)
            .build()

        appComponent.inject(this)
    }


}