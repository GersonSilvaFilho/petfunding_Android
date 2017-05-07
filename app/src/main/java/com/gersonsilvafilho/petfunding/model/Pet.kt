package com.gersonsilvafilho.petfunding.model

import java.util.*

/**
 * Created by GersonSilva on 5/6/17.
 */
class Pet {

    constructor()
    {
    }

    var name:String = ""

    var description:String? = null

    var type:String = "unknown"
    var sex:String = ""
    var birthDate:Date? = null
    var size:String = ""
    var furSize:String = ""
    var furColor:String = ""

    var vaccinated:Boolean = false
    var dewormed:Boolean = false
    var castrated:Boolean = false

    var likeChildren:Boolean = false
    var likeAnimals:Boolean = false
    var likeElders:Boolean = false
    var behaviour:List<String>? = null

    var state:String = ""
    var city:String = ""
    var contactName:String = ""
    var contactPhone:String = ""
    var ongName:String = ""

    var photosUrl:List<String>? = null

    var includedBy:String = ""
    var createdAt:Date = Date()
    var adoptedAt:Date? = null
    var updatedAt:Date = Date()
    var adoptedBy:String? = null

}