package com.gersonsilvafilho.petfunding.chat

import com.gersonsilvafilho.petfunding.model.message.Message
import com.gersonsilvafilho.petfunding.model.pet.Pet
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
        fun clearMessageBox()
    }

    interface Presenter
    {
        fun sendMessage(message:String)
        fun initChat(pet: Pet, userId: String?)
    }
}