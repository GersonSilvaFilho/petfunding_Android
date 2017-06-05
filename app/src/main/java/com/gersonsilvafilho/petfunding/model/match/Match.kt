package com.gersonsilvafilho.petfunding.model.user

import com.google.firebase.database.Exclude
import java.util.*

/**
 * Created by GersonSilva on 5/11/17.
 */
class Match {

    var uid:String = ""
    var petId:String = ""
    var date:Date = Date()
    var chatId:String = ""
    var userId:String = ""

    @Exclude
    fun toMap(): Map<String, Any> {
        val values = HashMap<String, Any>()
        values.put("uid", uid)
        values.put("petId", petId)
        values.put("date", date)
        values.put("chatId", chatId)
        values.put("userId", userId)
        return values

    }
}