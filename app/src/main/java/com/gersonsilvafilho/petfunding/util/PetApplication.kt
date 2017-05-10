package com.gersonsilvafilho.petfunding.util

import android.app.Application
import com.google.firebase.FirebaseApp
import com.miguelbcr.ui.rx_paparazzo2.RxPaparazzo

/**
 * Created by GersonSilva on 5/7/17.
 */
class PetApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        RxPaparazzo.register(this)
        FirebaseApp.initializeApp(this)
    }
}