package com.gersonsilvafilho.petfunding.dagger

import com.gersonsilvafilho.petfunding.model.match.MatchFirebaseRepository
import com.gersonsilvafilho.petfunding.model.match.MatchReposity
import com.gersonsilvafilho.petfunding.model.pet.PetFirebaseRepository
import com.gersonsilvafilho.petfunding.model.pet.PetRepository
import com.gersonsilvafilho.petfunding.model.user.UserFirebaseRepository
import com.gersonsilvafilho.petfunding.model.user.UserRepository
import dagger.Module
import dagger.Provides
import dagger.android.support.AndroidSupportInjectionModule


/**
 * Created by GersonSilva on 5/19/17.
 */
@Module(includes = [AndroidSupportInjectionModule::class])
class AppModule {
    @Provides
    fun providePetRepository(): PetRepository {
        return PetFirebaseRepository()
    }

    @Provides
    fun provideMatchRepository(): MatchReposity {
        return MatchFirebaseRepository()
    }

    @Provides
    fun provideUserRepository(): UserRepository {
        return UserFirebaseRepository()
    }
}