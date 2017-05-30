package com.gersonsilvafilho.petfunding.model.pet

import com.google.firebase.database.Exclude
import java.io.Serializable
import java.util.*


/**
 * Created by GersonSilva on 5/6/17.
 */
class Pet : Serializable {

    var uid: String = ""

    var name: String = ""

    var description: String = ""

    var type: String = ""

    var sex: String = ""

    var birthDate:Date = Date()

    var size: String = ""

    var furSize: String = ""

    var furColors: ArrayList<String> = ArrayList<String>()

    var vaccinated: Boolean = false

    var dewormed: Boolean = false

    var castrated: Boolean = false

    var likeChildren: Boolean = false

    var likeAnimals: Boolean = false

    var likeElders: Boolean = false

    var hasLocomotionProblems: Boolean = false

    var blind: Boolean = false

    var hasBadBehaviour: Boolean = false

    var behaviour: ArrayList<String> = ArrayList<String>()

    var state: String = ""

    var city: String = ""

    var contactName: String = ""

    var contactPhone: String = ""

    var ongName: String = ""

    var photosUrl: ArrayList<String> = ArrayList<String>()

    var createdBy: String = ""

    var createdAt: Date = Date()

    var adoptedAt: Date? = null

    var updatedAt: Date = Date()

    var adoptedBy: String? = null

    @Exclude
    fun toMap(): Map<String, Any> {
        val values = HashMap<String, Any>()

        values.put("uid", uid)
        values.put("name", name)
        values.put("description", description)
        values.put("type", type)
        values.put("sex", sex)
        values.put("birthDate", birthDate)
        values.put("size", size)
        values.put("furSize", furSize)
        values.put("furColors", furColors)
        values.put("vaccinated", vaccinated)
        values.put("dewormed", dewormed)
        values.put("castrated", castrated)
        values.put("likeChildren", likeChildren)
        values.put("likeAnimals", likeAnimals)
        values.put("likeElders", likeElders)
        values.put("hasLocomotionProblems", hasLocomotionProblems)
        values.put("blind", blind)
        values.put("hasBadBehaviour", hasBadBehaviour)
        values.put("behaviour", behaviour)
        values.put("city", city)
        values.put("contactName", contactName)
        values.put("contactPhone", contactPhone)
        values.put("ongName", ongName)
        values.put("photosUrl", photosUrl)
        values.put("createdBy", createdBy)
        values.put("createdAt", createdAt)
        if(adoptedAt != null) values.put("adoptedAt", adoptedAt!!)
        values.put("updatedAt", updatedAt)
        if(adoptedBy != null) values.put("adoptedBy", adoptedBy!!)
        return values

    }
}