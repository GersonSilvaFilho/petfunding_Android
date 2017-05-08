package com.gersonsilvafilho.petfunding.model

import com.google.firebase.database.Exclude
import java.util.*



/**
 * Created by GersonSilva on 5/6/17.
 */
class Pet {

    constructor()
    {
    }

    var uid:String = ""
    var name:String = ""
    var description:String = ""

    var type:String = "unknown"
    var sex:String = ""
    //var birthDate:Date? = null
    var size:String = ""
    var furSize:String = ""
    var furColors:ArrayList<String> = ArrayList<String>()

    var isVaccinated:Boolean = false
    var isDewormed:Boolean = false
    var isCastrated:Boolean = false

    var likeChildren:Boolean = false
    var likeAnimals:Boolean = false
    var likeElders:Boolean = false

    var hasLocomotionProblems:Boolean = false
    var isBlind:Boolean = false
    var hasBadBehaviour:Boolean = false

    var behaviour:ArrayList<String> = ArrayList<String>()

    var state:String = ""
    var city:String = ""
    var contactName:String = ""
    var contactPhone:String = ""
    var ongName:String = ""

    var photosUrl:ArrayList<String> = ArrayList<String>()

    var createdBy:String = ""
    var createdAt:Date = Date()
    var adoptedAt:Date? = null
    var updatedAt:Date = Date()
    var adoptedBy:String? = null


    @Exclude
    fun toMap(): Map<String, Any> {
        val values = HashMap<String, Any>()

        values.put("uid", uid)
        values.put("name", name)
        values.put("description", description)
        values.put("type", type)
        values.put("sex", sex)
        values.put("size", size)
        values.put("furSize", furSize)
        values.put("furColors", furColors)
        values.put("isVaccinated", isVaccinated)
        values.put("isDewormed", isDewormed)
        values.put("isCastrated", isCastrated)
        values.put("likeChildren", likeChildren)

        values.put("likeAnimals", likeAnimals)
        values.put("likeElders", likeElders)
        values.put("hasLocomotionProblems", hasLocomotionProblems)
        values.put("isBlind", isBlind)
        values.put("hasBadBehaviour", hasBadBehaviour)
        values.put("behaviour", behaviour)

        values.put("photosUrl", photosUrl)
        return values

    }

}