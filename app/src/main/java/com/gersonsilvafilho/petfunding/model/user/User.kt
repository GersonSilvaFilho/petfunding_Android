package com.gersonsilvafilho.petfunding.model.user

import com.google.firebase.database.Exclude
import com.stfalcon.chatkit.commons.models.IUser
import java.util.*

/**
 * Created by GersonSilva on 5/11/17.
 */
class User: IUser {

    @Exclude
    override fun getAvatar(): String {
        return imageUrl
    }

    @Exclude
    override fun getName(): String {
        return username
    }

    override fun getId(): String {
        return uid
    }

    var uid:String = ""
    var username:String = ""
    var matches: Map<String, Match> = HashMap<String, Match>()
    var unmatches: ArrayList<String> = ArrayList<String>()
    var email:String = ""
    var gender:String = ""
    var imageUrl:String = ""
    var fbId:String = ""

    @Exclude
    fun toMap(): Map<String, Any> {
        val values = HashMap<String, Any>()
        values.put("username", username)
        values.put("matches", matches)
        values.put("unmatches", unmatches)
        values.put("email", email)
        values.put("gender", gender)
        values.put("imageUrl", imageUrl)
        values.put("fbId", fbId)
        return values

    }
}