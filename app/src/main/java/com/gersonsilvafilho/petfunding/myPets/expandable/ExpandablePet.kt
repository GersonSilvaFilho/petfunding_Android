package com.gersonsilvafilho.petfunding.myPets.expandable

import com.bignerdranch.expandablerecyclerview.Model.ParentObject
import com.gersonsilvafilho.petfunding.model.pet.Pet
import com.gersonsilvafilho.petfunding.model.user.User

/**
 * Created by GersonSilva on 6/2/17.
 */
class ExpandablePet(val pet: Pet): ParentObject {

    private lateinit var mChildrenList: MutableList<User>

    override fun setChildObjectList(p0: MutableList<Any>?) {
        mChildrenList = p0!!.toList() as MutableList<User>
    }

    override fun getChildObjectList(): MutableList<User> {
        return mChildrenList
    }
}