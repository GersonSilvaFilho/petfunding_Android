package com.gersonsilvafilho.petfunding.chat

import dagger.Module
import dagger.Provides

/**
 * Created by GersonSilva on 5/20/17.
 */
@Module
class ChatModule {
    @Provides
    fun provideMainMenuPresenter(): ChatContract.Presenter{
        return  ChatPresenter()
    }
}