package com.gersonsilvafilho.petfunding.myPets.expandable

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.gersonsilvafilho.petfunding.R
import com.gersonsilvafilho.petfunding.model.user.User
import com.squareup.picasso.Picasso


/**
 * Created by GersonSilva on 6/2/17.
 */
class MyPetsUserChildViewHolder : RecyclerView.ViewHolder{


    constructor(itemView: View):super(itemView)

    fun setUser(user:User)
    {
        val userChildNameText = this.itemView.findViewById(R.id.userChildNameTextView) as TextView
        userChildNameText.setText(user.name)

        val userChildImage = this.itemView.findViewById(R.id.userChildImage) as ImageView
        userChildNameText.setText(user.name)

        Picasso.with(this.itemView.context)
                .load(user.imageUrl)
                .into(userChildImage)
    }
}