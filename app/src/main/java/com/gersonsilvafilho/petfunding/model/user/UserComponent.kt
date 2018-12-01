package com.gersonsilvafilho.petfunding.model.user

import com.gersonsilvafilho.petfunding.chat.AddPetComponent
import com.gersonsilvafilho.petfunding.chat.AddPetModule
import com.gersonsilvafilho.petfunding.chat.ChatComponent
import com.gersonsilvafilho.petfunding.chat.ChatModule
import com.gersonsilvafilho.petfunding.likelist.LikeListComponent
import com.gersonsilvafilho.petfunding.likelist.LikeListModule
import com.gersonsilvafilho.petfunding.main.dagger.MainMenuComponent
import com.gersonsilvafilho.petfunding.main.dagger.MainMenuModule
import com.gersonsilvafilho.petfunding.mypets.MyPetsComponent
import com.gersonsilvafilho.petfunding.mypets.MyPetsModule
import com.gersonsilvafilho.petfunding.splash.dagger.SplashComponent
import com.gersonsilvafilho.petfunding.splash.dagger.SplashModule
import dagger.Subcomponent

/**
 * Created by GersonSilva on 5/21/17.
 */
@UserScope
@Subcomponent(modules = arrayOf(UserModule::class))
interface UserComponent
{
    fun plus(mainMenuModule: MainMenuModule): MainMenuComponent
    fun plus(chatModule: ChatModule):ChatComponent
    fun plus(splashActivityModule: SplashModule): SplashComponent
    fun plus(likeListModule: LikeListModule):LikeListComponent
    fun plus(addPetModule: AddPetModule):AddPetComponent
    fun plus(myPetsModule: MyPetsModule):MyPetsComponent
}