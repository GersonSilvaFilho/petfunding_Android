package com.gersonsilvafilho.petfunding.mypets.expandable

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.gersonsilvafilho.petfunding.R
import com.gersonsilvafilho.petfunding.model.pet.Pet
import com.gersonsilvafilho.petfunding.model.user.User
import com.squareup.picasso.Picasso


/**
 * Created by GersonSilva on 6/2/17.
 */
class MyPetsUserChildViewHolder : RecyclerView.ViewHolder{


    constructor(itemView: View):super(itemView)

    fun setUser(user: User,pet: Pet, startChatUser: (pet: Pet, userId:String) -> Unit)
    {
        val userChildNameText = this.itemView.findViewById<TextView>(R.id.userChildNameTextView)
        userChildNameText.setText(user.name)

        val userChildImage = this.itemView.findViewById<ImageView>(R.id.userChildImage)
        userChildNameText.setText(user.name)

        Picasso.get()
                .load(user.imageUrl)
                .into(userChildImage)

        itemView.findViewById<CardView>(R.id.cardLikeList)?.setOnClickListener {
            startChatUser(pet, user.uid)
        }
    }
}