package com.gersonsilvafilho.petfunding.chat

import com.gersonsilvafilho.petfunding.model.chat.ChatRepository
import com.gersonsilvafilho.petfunding.model.match.MatchReposity
import com.gersonsilvafilho.petfunding.model.pet.PetRepository
import com.gersonsilvafilho.petfunding.model.user.UserRepository
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

/**
 * Created by GersonSilva on 5/20/17.
 */
@Module
class ChatModule {
    @Provides
    fun provideMainChatPresenter(
        chatActivity: ChatActivity,
        chatRepository: ChatRepository,
        userRepository: UserRepository,
        matchReposity: MatchReposity,
        petRepository: PetRepository
    ): ChatContract.Presenter = ChatPresenter(chatActivity, chatRepository, userRepository, matchReposity, petRepository)

}

@Module
abstract class ChatActivityBuilderModule {
    @ContributesAndroidInjector(modules = [ChatModule::class])
    abstract fun contributeVoucherActivity(): ChatActivity
}