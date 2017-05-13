package com.gersonsilvafilho.petfunding.model.message;

import com.gersonsilvafilho.petfunding.model.user.User
import com.google.firebase.database.Exclude
import com.stfalcon.chatkit.commons.models.IMessage
import com.stfalcon.chatkit.commons.models.IUser
import java.util.*

/**
 * Created by GersonSilva on 5/11/17.
 */
class Message: IMessage {
    override fun getId(): String {
        return uid
    }

    override fun getCreatedAt(): Date {
       return date
    }

    override fun getUser(): IUser {
        return user
    }

    override fun getText(): String {
        return itext
    }

    var uid:String = ""
    var itext:String = ""
    var userId:String = ""
    var date:Date = Date()
    @Exclude
    var user:User = User()

    @Exclude
    fun toMap(): Map<String, Any> {
        val values = HashMap<String, Any>()
        values.put("uid", uid)
        values.put("userId", userId)
        values.put("itext", itext)
        values.put("date", date)
        return values

    }
}