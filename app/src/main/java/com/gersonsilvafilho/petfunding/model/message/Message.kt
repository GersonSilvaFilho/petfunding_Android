package com.gersonsilvafilho.petfunding.model.message;

import com.google.firebase.database.Exclude
import java.util.*

/**
 * Created by GersonSilva on 5/11/17.
 */
class Message{

    var uid:String = ""
    var text:String = ""
    var userId:String = ""
    var date:Date = Date()

    @Exclude
    fun toMap(): Map<String, Any> {
        val values = HashMap<String, Any>()
        values.put("uid", uid)
        values.put("petId", userId)
        values.put("text", text)
        values.put("date", date)
        return values

    }
}