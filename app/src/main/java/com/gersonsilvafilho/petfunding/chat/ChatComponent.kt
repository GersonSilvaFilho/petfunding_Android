package com.gersonsilvafilho.petfunding.chat

import com.gersonsilvafilho.petfunding.util.ActivityScope
import dagger.Subcomponent

/**
 * Created by GersonSilva on 5/20/17.
 */
@ActivityScope
@Subcomponent(modules = arrayOf(ChatModule::class))
interface ChatComponent {
    fun inject(activity: ChatActivity)
}