package com.gersonsilvafilho.petfunding.model.chat

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by GersonSilva on 5/20/17.
 */
@Module
class ChatRepositoryModule {
    @Provides
    @Singleton
    fun module(): ChatRepository
    {
        return ChatFirebaseRepository()
    }
}