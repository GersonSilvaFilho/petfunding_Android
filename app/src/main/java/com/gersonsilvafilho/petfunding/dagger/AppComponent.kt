package com.gersonsilvafilho.petfunding.dagger

import android.app.Application
import com.gersonsilvafilho.petfunding.chat.ChatActivityBuilderModule
import com.gersonsilvafilho.petfunding.main.dagger.MainMenuBuilderModule
import com.gersonsilvafilho.petfunding.model.chat.ChatRepositoryModule
import com.gersonsilvafilho.petfunding.model.pet.MatchModule
import com.gersonsilvafilho.petfunding.model.pet.PetModule
import com.gersonsilvafilho.petfunding.model.user.UserModule
import com.gersonsilvafilho.petfunding.splash.dagger.FilterComponent
import com.gersonsilvafilho.petfunding.splash.dagger.FilterModule
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
        PetModule::class,
        MatchModule::class,
        UserModule::class,
        ChatActivityBuilderModule::class,
        MainMenuBuilderModule::class]
)
interface AppComponent {
    fun inject(application: PetApplication)

    fun plus(filterModule: FilterModule): FilterComponent

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}