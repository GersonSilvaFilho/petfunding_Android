package com.gersonsilvafilho.petfunding.util

import android.app.Application
import android.content.Context
import com.gersonsilvafilho.petfunding.dagger.AppComponent
import com.gersonsilvafilho.petfunding.dagger.AppModule
import com.gersonsilvafilho.petfunding.dagger.DaggerAppComponent
import com.gersonsilvafilho.petfunding.model.user.UserComponent
import com.gersonsilvafilho.petfunding.model.user.UserFirebaseRepository
import com.gersonsilvafilho.petfunding.model.user.UserModule
import com.google.firebase.FirebaseApp
import com.google.firebase.database.FirebaseDatabase
import com.miguelbcr.ui.rx_paparazzo2.RxPaparazzo







/**
 * Created by GersonSilva on 5/7/17.
 */
class PetApplication : Application() {

    private var userComponent: UserComponent? = null
    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        RxPaparazzo.register(this)
        FirebaseApp.initializeApp(this)
        FirebaseDatabase.getInstance().setPersistenceEnabled(true)
        initAppComponent()
    }

    fun get(context: Context): PetApplication {
        return context.getApplicationContext() as PetApplication
    }

    private fun initAppComponent() {
        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
    }


    fun getAppComponent(): AppComponent {
        return appComponent
    }

    fun createUserComponent(): UserComponent {
        userComponent = appComponent.plus(UserModule(UserFirebaseRepository()))
        return userComponent!!
    }

    fun releaseUserComponent() {
        userComponent = null
    }

    fun getUserComponent(): UserComponent? {
        return userComponent
    }

}