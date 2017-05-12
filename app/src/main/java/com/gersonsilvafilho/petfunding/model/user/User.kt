package com.gersonsilvafilho.petfunding.model.user

import com.google.firebase.database.Exclude
import com.stfalcon.chatkit.commons.models.IUser
import java.util.*

/**
 * Created by GersonSilva on 5/11/17.
 */
class User: IUser {


    override fun getAvatar(): String {
        return ""
    }

    override fun getName(): String {
        return username
    }

    override fun getId(): String {
        return uid
    }

    var uid:String = ""
    var username:String = ""
    var matchs: Map<String, Match> = HashMap<String, Match>()
    var unmatches: ArrayList<String> = ArrayList<String>()

    @Exclude
    fun toMap(): Map<String, Any> {
        val values = HashMap<String, Any>()
        values.put("name", username)
        values.put("matches", matchs)
        values.put("unmatches", unmatches)
        return values

    }
}