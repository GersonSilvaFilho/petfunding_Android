package com.gersonsilvafilho.petfunding.dagger

import com.gersonsilvafilho.petfunding.model.chat.ChatRepositoryModule
import com.gersonsilvafilho.petfunding.model.pet.MatchModule
import com.gersonsilvafilho.petfunding.model.pet.PetModule
import com.gersonsilvafilho.petfunding.model.user.UserComponent
import com.gersonsilvafilho.petfunding.model.user.UserModule
import dagger.Component
import javax.inject.Singleton



/**
 * Created by GersonSilva on 5/19/17.
 */
@Singleton
@Component(modules = arrayOf(AppModule::class, ChatRepositoryModule::class, PetModule::class, MatchModule::class))
interface AppComponent {
    fun plus(userModule: UserModule):UserComponent
}