package com.gersonsilvafilho.petfunding.model.chat

import com.gersonsilvafilho.petfunding.model.message.Message
import io.reactivex.Observable
import io.reactivex.Single

/**
 * Created by GersonSilva on 5/12/17.
 */
interface ChatRepository
{
    fun getChatFromId(chatId:String): Observable<Chat>
    fun sendMessage(chatId:String, message:Message): Single<String>
    fun initNewChat(matchId: String, userId: String): Single<String>
    fun listenMessages(chatId: String): Observable<Message>
}