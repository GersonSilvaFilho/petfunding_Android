package com.gersonsilvafilho.petfunding.chat

import com.gersonsilvafilho.petfunding.model.chat.ChatRepositoryModule
import com.gersonsilvafilho.petfunding.model.user.UserModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by GersonSilva on 5/20/17.
 */
@Singleton
@Component(modules = arrayOf(UserModule::class, ChatRepositoryModule::class, ChatModule::class))
interface ChatComponent {
    fun inject(activity: ChatActivity)
    fun inject(presenter: ChatPresenter)
}