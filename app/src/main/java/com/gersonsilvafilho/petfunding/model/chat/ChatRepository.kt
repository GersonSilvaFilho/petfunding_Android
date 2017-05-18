package com.gersonsilvafilho.petfunding.model.chat

import com.gersonsilvafilho.petfunding.model.message.Message
import io.reactivex.Completable
import io.reactivex.Observable

/**
 * Created by GersonSilva on 5/12/17.
 */
interface ChatRepository
{
    fun loadChatMessages(chatId:String): Observable<Chat>
    fun sendMessage(chatId:String, message:Message):Completable
    fun getCurrentChat():Chat
    fun initNewChat(matchId: String, userId: String): Completable
}