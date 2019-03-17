package com.gersonsilvafilho.petfunding.chat

import com.gersonsilvafilho.petfunding.model.chat.ChatRepository
import com.gersonsilvafilho.petfunding.model.match.MatchReposity
import com.gersonsilvafilho.petfunding.model.user.UserRepository
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

/**
 * Created by GersonSilva on 5/20/17.
 */
@Module
object ChatActivityModule {
    @JvmStatic
    @Provides
    fun provideMainChatPresenter(
        chatActivity: ChatActivity,
        chatRepository: ChatRepository,
        userRepository: UserRepository,
        matchReposity: MatchReposity
    ): ChatContract.Presenter = ChatPresenter(chatActivity, chatRepository, userRepository, matchReposity)

}

@Module
abstract class ChatActivityBuilderModule {
    @ContributesAndroidInjector(modules = [ChatActivityModule::class])
    abstract fun contributeVoucherActivity(): ChatActivity
}