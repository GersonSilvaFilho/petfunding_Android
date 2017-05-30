package com.gersonsilvafilho.petfunding.model.user

import com.gersonsilvafilho.petfunding.chat.AddPetComponent
import com.gersonsilvafilho.petfunding.chat.AddPetModule
import com.gersonsilvafilho.petfunding.chat.ChatComponent
import com.gersonsilvafilho.petfunding.chat.ChatModule
import com.gersonsilvafilho.petfunding.likeList.LikeListComponent
import com.gersonsilvafilho.petfunding.likeList.LikeListModule
import com.gersonsilvafilho.petfunding.main.MainMenuComponent
import com.gersonsilvafilho.petfunding.main.MainMenuModule
import com.gersonsilvafilho.petfunding.myPets.MyPetsComponent
import com.gersonsilvafilho.petfunding.myPets.MyPetsModule
import com.gersonsilvafilho.petfunding.splash.SplashComponent
import com.gersonsilvafilho.petfunding.splash.SplashModule
import dagger.Subcomponent

/**
 * Created by GersonSilva on 5/21/17.
 */
@UserScope
@Subcomponent(modules = arrayOf(UserModule::class))
interface UserComponent
{
    fun plus(mainMenuModule: MainMenuModule):MainMenuComponent
    fun plus(chatModule: ChatModule):ChatComponent
    fun plus(splashActivityModule: SplashModule): SplashComponent
    fun plus(likeListModule: LikeListModule):LikeListComponent
    fun plus(addPetModule: AddPetModule):AddPetComponent
    fun plus(myPetsModule: MyPetsModule):MyPetsComponent
}