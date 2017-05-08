package com.gersonsilvafilho.petfunding.model

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

    var description:String? = null

    var type:String = "unknown"
    var sex:String = ""
    var birthDate:Date? = null
    var size:String = ""
    var furSize:String = ""
    var furColors:List<String>? = null

    var isVaccinated:Boolean = false
    var isDewormed:Boolean = false
    var isCastrated:Boolean = false

    var likeChildren:Boolean = false
    var likeAnimals:Boolean = false
    var likeElders:Boolean = false

    var hasLocomotionProblems:Boolean = false
    var isBlind:Boolean = false
    var hasBadBehaviour:Boolean = false

    var behaviour:List<String>? = null

    var state:String = ""
    var city:String = ""
    var contactName:String = ""
    var contactPhone:String = ""
    var ongName:String = ""

    var photosUrl:HashMap<String, String> = HashMap<String, String>()

    var createdBy:String = ""
    var createdAt:Date = Date()
    var adoptedAt:Date? = null
    var updatedAt:Date = Date()
    var adoptedBy:String? = null

}