package com.gersonsilvafilho.petfunding.chat

import com.gersonsilvafilho.petfunding.model.message.Message
import io.reactivex.Observable

/**
 * Created by GersonSilva on 5/12/17.
 */
interface ChatContract {
    interface View
    {
        fun addNewMessage(message:Message)
        fun onSendMessageClick(): Observable<Unit>
        fun onTextChange(): Observable<CharSequence>
        fun loadChatMessages(messages: List<Message>)
        fun initChatView(currentUserId: String)
    }

    interface Presenter
    {
        fun sendMessage(message:String)
    }
}