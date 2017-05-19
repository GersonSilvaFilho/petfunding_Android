package com.gersonsilvafilho.petfunding.model.chat

import com.gersonsilvafilho.petfunding.model.message.Message
import com.google.firebase.database.FirebaseDatabase
import durdinapps.rxfirebase2.RxFirebaseDatabase
import io.reactivex.Completable
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
    var usersRef = database.getReference("users")
    lateinit var mChat : Chat

    override fun loadChatMessages(chatId: String):Observable<Chat>
    {
        return RxFirebaseDatabase.observeSingleValueEvent(chatRef, Chat::class.java).toObservable()
    }

    override fun getCurrentChat(): Chat {
        return mChat
    }


    override fun sendMessage(chatId: String, message: Message): Single<String>
    {
        val key = chatRef.child(chatId).child("messages").push()
        message.uid = key.key
        return RxFirebaseDatabase.updateChildren(key,message.toMap()).toSingle { key.key }
    }

    override fun initNewChat(matchId: String, userId: String): Completable {
        val key = chatRef.push()
        mChat = Chat()
        mChat.uid = key.key
        val matchAddRef = usersRef.child(userId).child("matchs").child(matchId)
        val values = HashMap<String, Any>()
        values.put("chatId", key.key)
        return RxFirebaseDatabase.updateChildren(key,mChat.toMap())
                .doOnComplete { RxFirebaseDatabase.updateChildren(matchAddRef, values).subscribe() }
    }
}