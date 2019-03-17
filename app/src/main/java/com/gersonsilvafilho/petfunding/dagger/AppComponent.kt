package com.gersonsilvafilho.petfunding.dagger

import android.app.Application
import com.gersonsilvafilho.petfunding.chat.AddPetActivityBuilderModule
import com.gersonsilvafilho.petfunding.chat.ChatActivityBuilderModule
import com.gersonsilvafilho.petfunding.detail.DetailActivityBuilderModule
import com.gersonsilvafilho.petfunding.likelist.LikeListActivityBuilderModule
import com.gersonsilvafilho.petfunding.main.dagger.MainMenuBuilderModule
import com.gersonsilvafilho.petfunding.model.chat.ChatRepositoryModule
import com.gersonsilvafilho.petfunding.mypets.MyPetsActivityBuilderModule
import com.gersonsilvafilho.petfunding.splash.dagger.FilterActivityBuilderModule
import com.gersonsilvafilho.petfunding.splash.dagger.SplashActivityBuilderModule
import com.gersonsilvafilho.petfunding.util.PetApplication
import dagger.BindsInstance
import dagger.Component


/**
 * Created by GersonSilva on 5/19/17.
 */
@Component(
    modules = [
        AppModule::class,
        ChatRepositoryModule::class,
        ChatActivityBuilderModule::class,
        MainMenuBuilderModule::class,
        FilterActivityBuilderModule::class,
        LikeListActivityBuilderModule::class,
        MyPetsActivityBuilderModule::class,
        SplashActivityBuilderModule::class,
        AddPetActivityBuilderModule::class,
        DetailActivityBuilderModule::class
    ]
)
interface AppComponent {
    fun inject(application: PetApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}