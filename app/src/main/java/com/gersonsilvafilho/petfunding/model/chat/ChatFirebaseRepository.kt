package com.gersonsilvafilho.petfunding.model.chat

import com.gersonsilvafilho.petfunding.model.message.Message
import com.google.firebase.database.FirebaseDatabase
import durdinapps.rxfirebase2.RxFirebaseDatabase
import io.reactivex.Observable
import io.reactivex.Single
import java.util.*

/**
 * Created by GersonSilva on 5/12/17.
 */
class ChatFirebaseRepository : ChatRepository
{
    val database = FirebaseDatabase.getInstance()
    var chatRef = database.getReference("chat")
    var matchesRef = database.getReference("matches")

    override fun getChatFromId(chatId: String):Observable<Chat>
    {
        return RxFirebaseDatabase.observeSingleValueEvent(chatRef.child(chatId), Chat::class.java).toObservable()
    }

    override fun listenMessages(chatId: String):Observable<Message>
    {
        val ref = chatRef.child(chatId).child("messages")
        return RxFirebaseDatabase.observeValueEvent(ref, Message::class.java).toObservable()
    }


    override fun sendMessage(chatId: String, message: Message): Single<String>
    {
        val key = chatRef.child(chatId).child("messages").push()
        message.uid = key.key
        return RxFirebaseDatabase.updateChildren(key,message.toMap()).toSingle { key.key }
    }

    override fun initNewChat(matchId: String, userId: String): Single<String> {
        val key = chatRef.push()
        val mChat = Chat()
        mChat.uid = key.key
        val matchAddRef = matchesRef.orderByChild("userId").equalTo(userId)
        val values = HashMap<String, Any>()
        values.put("chatId", key.key)
        return RxFirebaseDatabase.updateChildren(key,mChat.toMap()).toSingle { mChat.uid }
    }
}