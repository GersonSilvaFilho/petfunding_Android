package com.gersonsilvafilho.petfunding.model.chat

import com.gersonsilvafilho.petfunding.model.message.Message
import com.google.firebase.database.Exclude
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by GersonSilva on 5/12/17.
 */
class Chat {

    var uid:String = ""
    var createdAt: Date = Date()
    var isRemoved:Boolean = false
    var messages: Map<String, Message> = HashMap<String, Message>()
    var userIds:ArrayList<String> = ArrayList<String>();

    @Exclude
    fun toMap(): Map<String, Any> {
        val values = HashMap<String, Any>()
        values.put("uid", uid)
        values.put("createdAt", createdAt)
        values.put("isRemoved", isRemoved)
        values.put("messages", messages)
        values.put("userIds", userIds)
        return values

    }
}