package com.gersonsilvafilho.petfunding.chat

import com.gersonsilvafilho.petfunding.model.chat.ChatRepository
import com.gersonsilvafilho.petfunding.model.user.UserRepository
import dagger.Module
import dagger.Provides

/**
 * Created by GersonSilva on 5/20/17.
 */
@Module
class ChatModule(val chatActivity: ChatActivity) {
    @Provides
    fun provideMainChatPresenter(chatRepository: ChatRepository, userRepository: UserRepository): ChatContract.Presenter{
        return ChatPresenter(chatActivity, chatRepository, userRepository)
    }
}