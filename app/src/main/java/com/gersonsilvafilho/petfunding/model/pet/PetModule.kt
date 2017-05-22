package com.gersonsilvafilho.petfunding.model.pet

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by GersonSilva on 5/20/17.
 */
@Module
class PetModule{

    @Provides
    @Singleton
    fun module(): PetRepository
    {
        return PetFirebaseRepository()
    }

}