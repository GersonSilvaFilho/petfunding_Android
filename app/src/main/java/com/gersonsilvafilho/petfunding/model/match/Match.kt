package com.gersonsilvafilho.petfunding.model.user

import com.google.firebase.database.Exclude
import java.util.*

/**
 * Created by GersonSilva on 5/11/17.
 */
class Match {

    var petId:String = ""
    var date:Date = Date()
    var chatId:String = ""

    @Exclude
    fun toMap(): Map<String, Any> {
        val values = HashMap<String, Any>()
        values.put("petId", petId)
        values.put("date", date)
        values.put("chatId", chatId)
        return values

    }
}