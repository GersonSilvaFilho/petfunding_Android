package com.gersonsilvafilho.petfunding.model.chat

import dagger.Module
import dagger.Provides

/**
 * Created by GersonSilva on 5/20/17.
 */
@Module
object ChatRepositoryModule {
    @JvmStatic
    @Provides
    fun module(): ChatRepository
    {
        return ChatFirebaseRepository()
    }
}