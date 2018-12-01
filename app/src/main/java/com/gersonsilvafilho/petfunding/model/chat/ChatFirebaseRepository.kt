package com.gersonsilvafilho.petfunding.model.chat

import android.util.Log
import com.gersonsilvafilho.petfunding.model.message.Message
import com.google.firebase.database.FirebaseDatabase
import durdinapps.rxfirebase2.RxFirebaseChildEvent
import durdinapps.rxfirebase2.RxFirebaseDatabase
import io.reactivex.Observable
import io.reactivex.Single
import java.util.HashMap

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
        return RxFirebaseDatabase.observeSingleValueEvent(chatRef.child(chatId), Chat::class.java)
                .filter { m -> m != null }
                .doOnError { t -> Log.i("ChatFirebaseRepository", t.message) }
                .toObservable()
    }

    override fun listenMessages(chatId: String): Observable<RxFirebaseChildEvent<Message>>
    {
        val ref = chatRef.child(chatId).child("messages")
        return RxFirebaseDatabase.observeChildEvent(ref, Message::class.java)
                .filter { m -> m != null }
                .toObservable()
    }


    override fun sendMessage(chatId: String, message: Message): Single<String>
    {
        val key = chatRef.child(chatId).child("messages").push()
        message.uid = key.key!!
        return RxFirebaseDatabase.updateChildren(key,message.toMap()).toSingle { key.key }
    }

    override fun initNewChat(matchId: String, userId: String): Single<String> {
        val key = chatRef.push()
        val mChat = Chat()
        mChat.uid = key.key!!
        val matchAddRef = matchesRef.orderByChild("userId").equalTo(userId)
        val values = HashMap<String, Any>()
        values.put("chatId", key.key!!)
        return RxFirebaseDatabase.updateChildren(key,mChat.toMap()).toSingle { mChat.uid }
    }
}