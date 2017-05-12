package com.gersonsilvafilho.petfunding.model.user

import com.google.firebase.database.Exclude
import java.util.*

/**
 * Created by GersonSilva on 5/11/17.
 */
class User {

    var name:String = ""
    var matchs: Map<String, Match> = HashMap<String, Match>()
    var unmatches: ArrayList<String> = ArrayList<String>()

    @Exclude
    fun toMap(): Map<String, Any> {
        val values = HashMap<String, Any>()
        values.put("name", name)
        values.put("matches", matchs)
        values.put("unmatches", unmatches)
        return values

    }
}