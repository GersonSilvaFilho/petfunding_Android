package com.gersonsilvafilho.petfunding.model.chat

import com.gersonsilvafilho.petfunding.model.message.Message
import com.gersonsilvafilho.petfunding.model.user.Match
import io.reactivex.Completable
import io.reactivex.Observable

/**
 * Created by GersonSilva on 5/12/17.
 */
interface ChatRepository
{
    fun loadChatMessages(chatId:String): Observable<Chat>
    fun initNewChat(matchId: Match): Completable
    fun sendMessage(chatId:String, message:Message):Completable
    fun getCurrentChat():Chat
}