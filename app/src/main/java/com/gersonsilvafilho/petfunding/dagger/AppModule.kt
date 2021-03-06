package com.gersonsilvafilho.petfunding.dagger

import com.gersonsilvafilho.petfunding.model.chat.ChatFirebaseRepository
import com.gersonsilvafilho.petfunding.model.chat.ChatRepository
import com.gersonsilvafilho.petfunding.model.match.MatchFirebaseRepository
import com.gersonsilvafilho.petfunding.model.match.MatchReposity
import com.gersonsilvafilho.petfunding.model.pet.PetFirebaseRepository
import com.gersonsilvafilho.petfunding.model.pet.PetRepository
import com.gersonsilvafilho.petfunding.model.user.UserFirebaseRepository
import com.gersonsilvafilho.petfunding.model.user.UserRepository
import dagger.Module
import dagger.Provides
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


/**
 * Created by GersonSilva on 5/19/17.
 */
@Module(includes = [AndroidSupportInjectionModule::class])
object AppModule {

    @Singleton
    @Provides
    @JvmStatic
    fun providePetRepository(): PetRepository {
        return PetFirebaseRepository()
    }

    @Singleton
    @Provides
    @JvmStatic
    fun provideMatchRepository(): MatchReposity {
        return MatchFirebaseRepository()
    }

    @Singleton
    @Provides
    @JvmStatic
    fun provideUserRepository(): UserRepository {
        return UserFirebaseRepository()
    }

    @Singleton
    @JvmStatic
    @Provides
    fun module(): ChatRepository {
        return ChatFirebaseRepository()
    }
}