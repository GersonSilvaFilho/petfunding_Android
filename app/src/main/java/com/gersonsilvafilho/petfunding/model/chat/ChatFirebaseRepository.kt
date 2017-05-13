package com.gersonsilvafilho.petfunding.model.chat

import com.gersonsilvafilho.petfunding.model.message.Message
import com.gersonsilvafilho.petfunding.model.user.Match
import com.google.firebase.database.FirebaseDatabase
import durdinapps.rxfirebase2.RxFirebaseDatabase
import io.reactivex.Completable
import io.reactivex.Observable

/**
 * Created by GersonSilva on 5/12/17.
 */
class ChatFirebaseRepository : ChatRepository
{


    val database = FirebaseDatabase.getInstance()
    var chatRef = database.getReference("chat")
    var matchRef = database.getReference("match")
    lateinit var mChat : Chat

    override fun loadChatMessages(chatId: String):Observable<Chat>
    {
        return RxFirebaseDatabase.observeSingleValueEvent(chatRef, Chat::class.java).toObservable()
    }

    override fun getCurrentChat(): Chat {
        return mChat
    }

    override fun initNewChat(match: Match): Completable {
        val key = chatRef.push()
        mChat = Chat()
        mChat.uid = key.key
        val matchAddRef = matchRef.child(match.uid)
        match.chatId = mChat.uid
        return RxFirebaseDatabase.updateChildren(key,mChat.toMap())
                .doOnComplete { RxFirebaseDatabase.updateChildren(matchAddRef, match.toMap()).subscribe() }
    }

    override fun sendMessage(chatId: String, message: Message): Completable
    {
        val key = chatRef.child(chatId).child("messages")
        return RxFirebaseDatabase.updateChildren(key,message.toMap()).doOnComplete {  }.doOnError {  }
    }
}