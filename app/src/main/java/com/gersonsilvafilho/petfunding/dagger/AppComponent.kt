package com.gersonsilvafilho.petfunding.dagger

import com.gersonsilvafilho.petfunding.chat.ChatActivity
import com.gersonsilvafilho.petfunding.chat.ChatContract
import com.gersonsilvafilho.petfunding.chat.ChatModule
import com.gersonsilvafilho.petfunding.model.chat.ChatRepositoryModule
import com.gersonsilvafilho.petfunding.model.user.UserModule
import com.gersonsilvafilho.petfunding.splash.SplashActivity
import com.gersonsilvafilho.petfunding.splash.SplashModule
import com.gersonsilvafilho.petfunding.splash.SplashPresenter
import com.gersonsilvafilho.petfunding.util.PetApplication
import dagger.Component
import javax.inject.Singleton

/**
 * Created by GersonSilva on 5/19/17.
 */
@Singleton
@Component(modules = arrayOf(AppModule::class, UserModule::class, ChatRepositoryModule::class, ChatModule::class, SplashModule::class))
interface AppComponent {
    fun inject(target: PetApplication)

    fun inject(activity: SplashActivity)
    fun inject(presenter: SplashPresenter)

    fun inject(activity: ChatActivity)
    fun inject(presenter: ChatContract.Presenter)
}